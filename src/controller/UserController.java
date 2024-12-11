package controller;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<User> users;

    public UserController() {
        this.users = new ArrayList<>();
    }

    // Método para registar um novo utilizador
    public boolean registerUser(User user) {
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                System.out.println("Erro: O email já está registado.");
                return false;
            }
        }
        users.add(user);
        System.out.println("Utilizador registado com sucesso: " + user.getName());
        return true;
    }

    // Método para autenticar um utilizador
    public User authenticateUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Autenticação bem-sucedida para o utilizador: " + user.getName());
                return user;
            }
        }
        System.out.println("Erro: Credenciais inválidas.");
        return null;
    }

    // Método para atribuir uma permissão a um utilizador
    public boolean assignRole(String email, String role) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setRole(role);
                System.out.println("Permissão atribuída com sucesso ao utilizador: " + user.getName());
                return true;
            }
        }
        System.out.println("Erro: Utilizador com email " + email + " não encontrado.");
        return false;
    }

    // Método para revogar uma permissão de um utilizador
    public boolean revokeRole(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setRole(null);
                System.out.println("Permissão revogada com sucesso do utilizador: " + user.getName());
                return true;
            }
        }
        System.out.println("Erro: Utilizador com email " + email + " não encontrado.");
        return false;
    }

    // Método para listar todos os utilizadores
    public List<User> listUsers() {
        return users;
    }
}
