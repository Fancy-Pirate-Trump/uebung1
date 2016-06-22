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
		
	}
	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		if(count <= id){
			entityTransaction.begin();
			Product product = (Product) entityManager.createQuery("SELECT p FROM Product p WHERE p.id = " + count)
																	.getSingleResult();
			count++;
			entityTransaction.commit();
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
		//entityManagerFac = Persistence.createEntityManagerFactory("openjpa", System.getProperties());
		entityManagerFac = getWithoutConfig();
		entityManager = entityManagerFac.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return "JPAStrategy";
	}

	@Override
	public void setCount(long value, long savedId) {
		// TODO Auto-generated method stub
		this.id = savedId;
		this.count = (savedId-value)+1;
	}

	@Override
	public long getHighestId() {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		long id = (long) entityManager.createQuery("SELECT MAX(p.id) FROM Product p")
													.getSingleResult();
		entityTransaction.commit();
		return id;
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
		map.put("openjpa.MetaDataFactory", "jpa(Types=" + application.Product.class.getName() + ")");


		return OpenJPAPersistence.getEntityManagerFactory(map);

	}

}
