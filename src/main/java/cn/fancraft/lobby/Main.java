package cn.fancraft.lobby;

import cn.fancraft.lobby.Data.LoadConfig;
import com.moandjiezana.toml.Toml;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.instance.*;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;
public class Main {
    public static Toml Config;
    public static void main(String[] args) {

        // ��ʼ������
        LoadConfig.loadConfig();
        // �ж��費��Ҫʹ��velocity����
        if (Config.getBoolean("velocity.enable")){
            VelocityProxy.enable(Config.getString("velocity.secret"));
            System.out.println("��ǰ����ʹ��Velocityģʽ��");
        }
        // ����MineCraft������
        MinecraftServer minecraftServer = MinecraftServer.init();

        // ����һ��ʵ��
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        // ��������������
        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        // ���һ��������ɵ��¼�
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0.5, 42, 0.5));

        });


        minecraftServer.start(Config.getString("server.host"), Math.toIntExact(Config.getLong("server.port")));
    }
}