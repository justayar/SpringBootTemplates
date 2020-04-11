package com.justayar.springboot.configuration;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config hazelcastConfig(){

        Config config = new Config();
        config.setNetworkConfig(getHazelcastNetworkConfig());
        config.setClusterName("hazelcast-cluster");
        config.setInstanceName("hazelcast-template");
        config.setPartitionGroupConfig(getPartitionGroupConfig());
        config.setProperty("hazelcast.health.monitoring.level","NOISY");
        config.addMapConfig(getHazelcastMapConfig());


        return config;
    }

    private MapConfig getHazelcastMapConfig(){

        MapConfig mapConfig = new MapConfig();

        // Map Name Property

        mapConfig.setName("hazelcast-map-config");

        //Map Backup Properties:

        mapConfig.setBackupCount(2); // it's default 1
        mapConfig.setAsyncBackupCount(1); // it's default 0

        // -----------------------------------------------------------

        // Map Expiration Properties:

        mapConfig.setMaxIdleSeconds(3600); // it will expire items if it is no write or read operation inside one hour.
        mapConfig.setTimeToLiveSeconds(3600); // it will expire items if it is no write operation inside one hour.

        // -----------------------------------------------------------


        // Map Eviction Properties:

        // This eviction config follows if one of the partition size exceeds 1000, it evicts least recently used element from  partition.

        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.LRU);  // You can use NONE,LRU or LFU.
        evictionConfig.setMaxSizePolicy(MaxSizePolicy.PER_PARTITION); // You can use PER_NODE,PER_PARTITION so on. Default is PER_NODE
        evictionConfig.setSize(1000);

        mapConfig.setEvictionConfig(evictionConfig);

        // -----------------------------------------------------------

        // Map Metadata Policy Property:


        mapConfig.setMetadataPolicy(MetadataPolicy.CREATE_ON_UPDATE); // It's default property is CREATE_ON_UPDATE.You can use OFF property to disable it.
        // It provides automatic pre-processing of various data types on update time to make queries faster.
        // -----------------------------------------------------------

        // Map Read Back Up Data Property:

        mapConfig.setReadBackupData(false); // It's default property is false. It improves performance while it can cause stale reads.

        mapConfig.addEntryListenerConfig(
                new EntryListenerConfig( "com.justayar.springboot.util.MapEntryListener",
                        true, false ) );

        return mapConfig;
    }

    private NetworkConfig getHazelcastNetworkConfig(){

        NetworkConfig networkConfig = new NetworkConfig().setPort(5900)  // You can specify the port of hazelcast. It's default is 5701.
                                  .setPortAutoIncrement(false);


        JoinConfig joinConfig = new JoinConfig(); // You can specify network join configurations with different configuration types:

                                                  //tcp-ip, multi cast,aws,azure,eureka so on...

        // TCP-IP Configuration

        TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setConnectionTimeoutSeconds(30); // timeout period for network connection.
        tcpIpConfig.setEnabled(true); // You can disable it by passing false to this option.

        List<String> memberList = new ArrayList<>();
        memberList.add("127.0.0.1:5900");
        tcpIpConfig.setMembers(memberList);

        joinConfig.setTcpIpConfig(tcpIpConfig);


        // Multi cast Configuration  ( Not recommended for production environment)

        // Members do not need to know the concrete addresses of the other members.

        MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setMulticastTimeoutSeconds(30);
        multicastConfig.setMulticastTimeToLive(255); // it is between 1 and 255. Default is 255
        multicastConfig.setEnabled(false); // You can enable it by passing it to true

        joinConfig.setMulticastConfig(multicastConfig);


        networkConfig.setJoin(joinConfig);

        return networkConfig;
    }

    private PartitionGroupConfig getPartitionGroupConfig(){

        return new PartitionGroupConfig().setEnabled(true)
                                          .setGroupType(PartitionGroupConfig.MemberGroupType.PER_MEMBER);
                                                //// default configuration, not supported if multiple instances run on same host.
    }
}
