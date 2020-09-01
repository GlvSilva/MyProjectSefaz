package br.com.project.users;

import br.com.project.users.dao.PhoneDao;
import br.com.project.users.dao.UserDao;
import br.com.project.users.entity.Phone;
import br.com.project.users.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersApplication {

    public static void main(String[] args) throws Exception {
        Users users = new Users();
        Phone phone = new Phone();
        List<Phone> phones = new ArrayList<>();
        users.setName("Maria");
        users.setEmail("Maria@hotmail.com");
        phone.setRegionCode("081");
        phone.setNumber("998979397");
        phone.setNumberType("TIM");
        phones.add(phone);
        users.setPhones(phones);

        UserDao dao = new UserDao();
        PhoneDao phoneDao = new PhoneDao();
        users = dao.saveUsers(users);
        phone = phoneDao.savePhone(phone);
        System.out.println("Salvando Usuario: " + users.getId() + ", " + users.getName() + "," + users.getEmail());

        Users users1 = dao.findById(users.getId());
        System.out.println("Consultando o  Usuario por ID" + users.getId() + ", " + users.getName() + "," + users.getEmail());

        users1.setName("Otavio");
        users1 = dao.saveUsers(users1);
        System.out.println("Alterando Usuario" + users1.getId() + ", " + users1.getName() + "," + users1.getEmail());

        //dao.removeUser(users1.getId());
        //System.out.println("Deletando Usuario" + users.getId());
    }
}
