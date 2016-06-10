package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.persistence.*;
import javax.persistence.EntityManagerFactory;

import org.apache.openjpa.persistence.OpenJPAPersistence;

import fpt.com.Product;

public class JPAStrategy extends SerializableStrategyClass{

	private EntityManagerFactory entityManagerFac;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private long id;
	private long count;

	public JPAStrategy(){
		//entityManagerFac = Persistence.createEntityManagerFactory("openjpa", System.getProperties());
		entityManagerFac = getWithoutConfig();
		entityManager = entityManagerFac.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		if(count <= id){
			Product product = (Product) entityManager.createQuery("SELECT * FROM products WHERE id = " + count);
			return product;
		}else
			return null;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub

		entityTransaction.begin();
		entityManager.persist(obj);
		entityTransaction.commit();

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		entityManager.close();
		entityManagerFac.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return "JPAStrategy";
	}

	@Override
	public void giveValue(long value, long savedId) {
		// TODO Auto-generated method stub
		this.id = savedId;
		this.count = (savedId-value)+1;
	}

	@Override
	public long giveId() {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		Product p = (Product) entityManager.createQuery("SELECT * FROM products ORDER BY id DESC LIMIT 1");
		return p.getId();
	}
	
	public static EntityManagerFactory getWithoutConfig() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("openjpa.ConnectionURL",
				"jdbc:postgresql://java.is.uni-due.de/ws1011");
		map.put("openjpa.ConnectionDriverName", "org.postgresql.Driver");
		map.put("openjpa.ConnectionUserName", "ws1011");
		map.put("openjpa.ConnectionPassword", "ftpw10");
		map.put("openjpa.RuntimeUnenhancedClasses", "supported");
		map.put("openjpa.jdbc.SynchronizeMappings", "false");

		// find all classes to registrate them
		List<Class<?>> types = new ArrayList<Class<?>>();
		types.add(Product.class);

		if (!types.isEmpty()) {
			StringBuffer buf = new StringBuffer();
			for (Class<?> c : types) {
				if (buf.length() > 0)
					buf.append(";");
				buf.append(c.getName());
			}
			// <class>Producer</class>
			map.put("openjpa.MetaDataFactory", "jpa(Types=" + buf.toString()
					+ ")");
		}

		return OpenJPAPersistence.getEntityManagerFactory(map);

	}

}
