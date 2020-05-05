package com.tactfactory.monprojetsb.monprojetsb.utils;

import java.util.List;

import com.tactfactory.monprojetsb.monprojetsb.entities.User;

public class UserUtil {

  public static boolean comparerSimple(User u1, User u2) {
    boolean result = false;

    if (u1.getFirstname().equals(u2.getFirstname())
        && u1.getId().equals(u2.getId()) && u1.getLastname().equals(u2.getLastname())) {
      result = true;
    }

    return result;
  }

  public static boolean comparerListSimple(List<User> dbUsers, List<User> serviceUsers) {
    boolean result = true;

    for (int i = 0; i < dbUsers.size(); i++) {
      if (!comparerSimple(dbUsers.get(i), serviceUsers.get(i))) {
        result = false;
      }
    }

    return result;
  }
}
