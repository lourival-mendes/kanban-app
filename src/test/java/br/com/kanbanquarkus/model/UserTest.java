package br.com.kanbanquarkus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testUser() {
        User user = new User();
        assertNotNull(user);

        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@example.com");
        user.setName("John Doe");
        user.setAvatar("avatar_url");
        user.setBio("Bio description");
        user.setLocation("Location");
        user.setWebsite("https://example.com");
        user.setLinkedin("linkedin_url");
        user.setGithub("github_url");
        user.setYoutube("youtube_url");
        user.setTwitch("twitch_url");
        user.setDiscord("discord_url");
        user.setSlack("slack_url");
        user.setTelegram("telegram_url");
        user.setWhatsapp("whatsapp_url");
        user.setActive(true);
        user.setEmailVerified(true);
        user.setTwoFactor(true);
        user.setDarkMode(true);
        user.setNotifications(true);

        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email@example.com", user.getEmail());
        assertEquals("John Doe", user.getName());
        assertEquals("avatar_url", user.getAvatar());
        assertEquals("Bio description", user.getBio());
        assertEquals("Location", user.getLocation());
        assertEquals("https://example.com", user.getWebsite());
        assertEquals("linkedin_url", user.getLinkedin());
        assertEquals("github_url", user.getGithub());
        assertEquals("youtube_url", user.getYoutube());
        assertEquals("twitch_url", user.getTwitch());
        assertEquals("discord_url", user.getDiscord());
        assertEquals("slack_url", user.getSlack());
        assertEquals("telegram_url", user.getTelegram());
        assertEquals("whatsapp_url", user.getWhatsapp());
        assertEquals(true, user.getActive());
        assertEquals(true, user.getEmailVerified());
        assertEquals(true, user.getTwoFactor());
        assertEquals(true, user.getDarkMode());
        assertEquals(true, user.getNotifications());
    }
}
