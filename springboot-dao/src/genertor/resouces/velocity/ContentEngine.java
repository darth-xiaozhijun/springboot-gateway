package velocity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * 19. * 20. * To change the template for this generated type comment go to 21.
 * * Window>Preferences>Java>Code Generation>Code and Comments 22.
 */
public class ContentEngine {
	private VelocityContext context = null;

	private Template template = null;

	// private String properties = null ;

	/**
	 * 
	 * @param properties
	 * @throws Exception
	 */
	public void init(String properties) throws Exception {
		if (properties != null && properties.trim().length() > 0) {
			Velocity.init(properties);
		} else {
			Velocity.init();
		}
		context = new VelocityContext();
	}

	public void init(Properties properties) throws Exception {

		Velocity.init(properties);
		context = new VelocityContext();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		context.put(key, value);
	}

	/**
	 * 设置模版
	 * 
	 * @param templateFile
	 *            模版文件
	 * @throws AppException
	 */
	public void setTemplate(String templateFile) throws Exception {
		try {
			template = Velocity.getTemplate(templateFile);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			throw new IOException(" error : cannot find template "
					+ templateFile);
		} catch (ParseErrorException pee) {
			throw new IOException(" Syntax error in template " + templateFile
					+ ":" + pee);
		} catch (Exception e) {
			throw new IOException(e.toString());
		}

	}

	/**
	 * 设置模版
	 * 
	 * @param templateFile
	 *            模版文件
	 * @throws AppException
	 */
	public void setTemplate(String templateFile, String characterSet)
			throws IOException {
		try {
			template = Velocity.getTemplate(templateFile, characterSet);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			throw new IOException(" error : cannot find template "
					+ templateFile);
		} catch (ParseErrorException pee) {
			throw new IOException(" Syntax error in template " + templateFile
					+ ":" + pee);
		} catch (Exception e) {
			throw new IOException(e.toString());
		}

	}

	/**
	 *
	 */
	public String toText() throws IOException {
		StringWriter sw = new StringWriter();
		try {
			template.merge(context, sw);
		} catch (Exception e) {
			throw new IOException(e.toString());
		}
		return sw.toString();
	}

	/**
	 * 
	 * @param fileName
	 */
	public void toFile(String fileName) throws IOException {
		try {
			StringWriter sw = new StringWriter();
			template.merge(context, sw);

			PrintWriter filewriter = new PrintWriter(new FileOutputStream(
					fileName), true);
			filewriter.println(sw.toString());
			filewriter.close();
			System.out.println(fileName);
		} catch (Exception e) {
			throw new IOException(e.toString());
		}

	}

	public  void generatorVelocity(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		ContentEngine content = new ContentEngine();
		try {
			Properties p = new Properties();

			Properties varp = new Properties();

			String path = args[1];

			p.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, path);
			// p.setProperty(Velocity.RUNTIME_LOG, path + "velocity.log");

			content.init(p);

			FileInputStream in = new FileInputStream(args[1] + args[2]);
			varp.load(in);

			content.setTemplate(args[3], "utf-8");

			Iterator it = varp.keySet().iterator();
			String key = "";
			String value = "";
			while (it.hasNext()) {
				key = (String) it.next();
				value = varp.getProperty(key);
				content.put(key, value);
			}
			content.put("pk", args[6]);
			content.put("Object_name", args[4]);

			content.put("object_name", args[4].replace(args[4].charAt(0),
					(char) (args[4].charAt(0) < 95 ? args[4].charAt(0) + 32
							: args[4].charAt(0))));
			content.put("create_time", sdf.format(new Date()));
			if (Integer.valueOf(args[0]) == 1) {

				content.toFile(args[5]  + "I"+args[4] + "Service" + ".java");
			}
			if (Integer.valueOf(args[0]) == 2) {
				content.toFile(args[5] +"impl/" + args[4] + "ServiceImpl"
						+ ".java");
			}
			if (Integer.valueOf(args[0]) == 3) {
				content.toFile(args[5] +"I"+ args[4] + "Mapper"
						+ ".java");
			}
		} catch (IOException ae) {
			ae.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}