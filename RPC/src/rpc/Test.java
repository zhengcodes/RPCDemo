package rpc;

import java.net.InetSocketAddress;

import rpc.client.RpcImporter;
import rpc.service.impl.EchoServiceImpl;
import rpc.service.EchoService;
import rpc.service.RpcExporter;

public class Test {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
					RpcExporter.exporter("localhost", 8088);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		RpcImporter<EchoService> importer = new RpcImporter<>();
		EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost",8088));
		System.out.println(echo.echo("Are you ok?"));
	}

}
