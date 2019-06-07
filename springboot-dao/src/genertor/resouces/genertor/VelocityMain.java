package genertor;

import java.io.File;

import velocity.ContentEngine;


public class VelocityMain {
	

	public static void main(String[] args) {

	}

	public  void genertor(final String modelName, final String pk) {
		// TODO Auto-generated method stub
		String projectBasePath = new File("").getAbsolutePath();

//		String daoBasePath = projectBasePath
//				+ "/src/main/java/com/springboot/dao/";

//		File daoFile = new File(daoBasePath);
//		if (!daoFile.exists())
//			new File(daoBasePath).mkdirs();
//		if (!filerFile(daoBasePath + modelName + "Mapper")) {
//
//			String[] args = new String[] { "3",
//					projectBasePath + "/src/genertor/resouces/velocity/",
//					"Object.properties", "ObjectMapper.vm", modelName,
//					daoBasePath, pk };
//
//			new ContentEngine().generatorVelocity(args);
//
//		
//		}
//
//		System.out.println("generator=" + modelName + "dao success");

		String servicesBasePath = projectBasePath
				+ "/../springboot-api/src/main/java/com/springboot/api/service/";

		File servicesFile = new File(servicesBasePath);
		if (!servicesFile.exists())
			new File(servicesBasePath).mkdirs();
		if (!filerFile(servicesBasePath + "I"+modelName + "Service")) {

			String[] args = new String[] { "1",
					projectBasePath + "/src/genertor/resouces/velocity/",
					"Object.properties", "ObjectServices.vm", modelName,
					servicesBasePath, pk };

			new ContentEngine().generatorVelocity(args);

			
		}

		System.out.println("generator=" + modelName + "servcice success");
		
		String serviceImplBasePath = projectBasePath
				+ "/../springboot-core/src/main/java/com/springboot/core/service/";

		File serviceImplFile = new File(serviceImplBasePath);
		if (!serviceImplFile.exists())
			new File(serviceImplBasePath).mkdirs();
		if (!filerFile(serviceImplBasePath + "I"+modelName + "Service")) {

			String[] args = new String[] { "2",
					projectBasePath + "/src/genertor/resouces/velocity/",
					"Object.properties", "ObjectServiceImpl.vm", modelName,
					serviceImplBasePath, pk };

			new ContentEngine().generatorVelocity(args);

			
		}

		System.out.println("generator=" + modelName + "servcice success");

	}

	private boolean filerFile(String fileName) {
		return fileName.contains("CompressedMapper.java");
	}
}
