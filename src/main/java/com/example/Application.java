package com.example;

import com.example.Repository.CriptoRepo;
import com.example.modelo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class Application {

	private static final String SALT = "puto";

	public static void main(String[] args) {
		// Inicializar la aplicación Spring Boot
		ApplicationContext context = SpringApplication.run(Application.class, args);

		// Obtener el repositorio de usuarios
		var repo = context.getBean(CriptoRepo.class);

		// Crear usuarios y guardarlos en la base de datos
		String password1 = hashPassword("pwd1"); // Guardamos el hash en lugar de la contraseña real
		String password2 = hashPassword("pwd2");
		String password3 = hashPassword("pwd3");
		repo.save(new User("usr1", password1));
		repo.save(new User("usr2", password2));
		repo.save(new User("usr3", password3));

		// Mostrar la contraseña del usuario usr1
		System.out.println("La contraseña del usuario usr1 es: pwd1");

		// Realizar inicio de sesión
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.println("Inicio de sesión");
				System.out.print("Nombre de usuario: ");
				String usernameInput = scanner.nextLine().trim(); // Eliminar espacios en blanco
				System.out.print("Contraseña: ");
				String passwordInput = scanner.nextLine().trim(); // Eliminar espacios en blanco
				// Hasheamos la contraseña ingresada antes de compararla
				String hashedPasswordInput = hashPassword(passwordInput);
				// Buscar el usuario en la base de datos
				User usuarioEncontrado = repo.findByNombreUsuario(usernameInput);
				// Verificar si el usuario existe y si la contraseña coincide
				if (usuarioEncontrado != null && verificarContrasenna(Objects.requireNonNull(hashedPasswordInput), usuarioEncontrado.getPassword())) {
					System.out.println("Inicio de sesión exitoso para el usuario: " + usernameInput);
					main.init();
					break; // Salir del bucle si el inicio de sesión es exitoso
				} else {
					System.out.println("Nombre de usuario o contraseña incorrectos.");
				}
			} while (true);
		}
	}
	public static String hashPassword(final String base) {
		try{
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(base.getBytes("UTF-8"));
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			System.out.println(hexString.toString());
			return hexString.toString();
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	private static boolean verificarContrasenna(String contrasennaIngresada, String hashContrasennaAlmacenada) {
		// Verificar si la contraseña ingresada es igual a "pwd1"
		if (contrasennaIngresada.equals("pwd1")||contrasennaIngresada.equals("pwd2") ||contrasennaIngresada.equals("pwd3")) {
			// Si es igual a "pwd1", asignamos su hash para comparación
			contrasennaIngresada = hashPassword(contrasennaIngresada);
		}
		// Verificar si los hashes coinciden
		return contrasennaIngresada.equals(hashContrasennaAlmacenada);
	}
}
