package br.com.kanbanquarkus.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MongoEntity(collection = "users")
public class User extends PanacheMongoEntity {

    public String username;
    public String password;
    public String email;
    public String name;
    public Role role;
    public String avatar;
    public String bio;
    public String location;
    public String website;
    public String linkedin;
    public String github;
    public String youtube;
    public String twitch;
    public String discord;
    public String slack;
    public String telegram;
    public String whatsapp;
    public Boolean active;
    public Boolean emailVerified;
    public Boolean twoFactor;
    public Boolean darkMode;
    public Boolean notifications;

}