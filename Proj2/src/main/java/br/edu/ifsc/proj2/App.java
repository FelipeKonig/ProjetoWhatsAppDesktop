package br.edu.ifsc.proj2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Chamada;
import br.edu.ifsc.proj2.model.Status;
import br.edu.ifsc.proj2.model.Usuario;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {

		scene = new Scene(loadFXML("Login"));

		Usuario usuario1 = new Usuario("Felipe", "000");
		Usuario usuario2 = new Usuario("Ana", "123");
		Usuario usuario3 = new Usuario("Jonas", "1234");
		Usuario usuario4 = new Usuario("Eduardo", "12345");
		Usuario usuario5 = new Usuario("Cristiano", "123456");

		Usuario contato1 = new Usuario("Lohane", "111");
		Usuario contato2 = new Usuario("Ramon", "222");
		Usuario contato3 = new Usuario("Jean", "333");
		Usuario contato4 = new Usuario("Diego", "444");
		Usuario contato5 = new Usuario("Carolina", "555");
		Usuario contato6 = new Usuario("Carlos", "666");
		Usuario contato7 = new Usuario("Thales", "777");
		Usuario contato8 = new Usuario("Vanessa", "888");
		Usuario contato9 = new Usuario("Eduardo", "999");
		Usuario contato10 = new Usuario("Elena", "1010");
		Usuario contato11 = new Usuario("Cleiton", "1111");
		Usuario contato12 = new Usuario("Tavares", "2222");
		Usuario contato13 = new Usuario("Lucas", "3333");
		Usuario contato14 = new Usuario("Rojie", "4444");
		Usuario contato15 = new Usuario("Thomas", "5555");
		Usuario contato16 = new Usuario("Rochelle", "6666");
		
		Chamada chamada1 = new Chamada("Ligação", "Carolina");
		
		Status status1 = new Status("A vida é cansativa", usuario2.getNome());
		Status status2 = new Status("Procrastinar é bom demais, só não sei se deveria fazer isso o tempo inteiro D: ", usuario3.getNome());
		Status status3 = new Status("Será que Joaquin Phoenix leva o Oscar? Espero que sim", usuario4.getNome());
		Status status4 = new Status("KKKKKKKKKKKK", usuario2.getNome());
		Status status5 = new Status("Que desgraça", usuario2.getNome());
		Status status6 = new Status("Dark é legal", usuario3.getNome());
		Status status7 = new Status("Scorpion ou Sub-Zero?", usuario3.getNome());

		usuario1.getContato().add(contato1);
		usuario1.getContato().add(contato2);
		usuario1.getContato().add(contato3);
		usuario1.getContato().add(contato4);
		usuario1.getContato().add(contato5);
		usuario1.getContato().add(contato6);
		usuario1.getContato().add(contato7);
		usuario1.getContato().add(contato8);
		usuario1.getContato().add(contato9);
		usuario1.getContato().add(contato10);
		usuario1.getContato().add(contato11);
		usuario1.getContato().add(contato12);
		usuario1.getContato().add(contato13);
		usuario1.getContato().add(contato14);
		usuario1.getContato().add(contato15);
		usuario1.getContato().add(contato16);
		usuario1.getChamadas().add(chamada1);

		usuario2.getContato().add(contato3);
		usuario2.getContato().add(contato4);
		usuario2.getContato().add(contato5);
		usuario2.getStatus().add(status1);
		usuario2.getStatus().add(status4);
		usuario2.getStatus().add(status5);

		usuario3.getContato().add(contato1);
		usuario3.getContato().add(contato4);
		usuario3.getContato().add(contato5);
		usuario3.getStatus().add(status2);
		usuario3.getStatus().add(status6);
		usuario3.getStatus().add(status7);

		usuario4.getContato().add(contato2);
		usuario4.getContato().add(contato4);
		usuario4.getContato().add(contato3);
		usuario4.getStatus().add(status3);

		EntityManager em = Conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);
		em.persist(usuario4);
		em.persist(usuario5);

		em.getTransaction().commit();
	
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		System.out.println("Usuários:");
		for (Usuario u : users)
			System.out.println(u.getNome());

		em.close();

		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

}