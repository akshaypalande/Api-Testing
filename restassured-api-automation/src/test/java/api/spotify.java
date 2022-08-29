package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class spotify {
    String userId="";
    String tokenValue="";
    String playlistId1="";
    String playlistId2="";

    @BeforeMethod
    public void setUp()
    {
        tokenValue="Bearer BQAgVTmY_Qb4QxK0uIveSHPhjvUjmwI-XeVaa96TN7nRvFbqbMugswlIAuYCiAHAxrbGVVN-mqFlAjRHxhVCTKc08cX-L0YjxES7Wg4ypAJZEP5DySsuubbeMCDtBWo0oi8rTkwh--YLjqoiJGeVSjBgBULZ7JBXM9g6jHL0DJI3VM0rCDGNhg-OcjmbY48";
    }

    //To get user details
    @Test(priority=1)
    public void getUserDetailsFromSpotifyApp()
    {
        String expectedUserId="	h7kd3jou86gvs8j3czapokgy4";
        String expectedEmailId="akshaypalande5@gmail.com";
        Response response1= RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        userId = response1.path("id");
        String actualEmailId=response1.path("email").toString();
        Assert.assertEquals(expectedUserId,userId);
        response1.getBody().asString();
        Assert.assertEquals(expectedEmailId,actualEmailId);
    }

    //To create new playlist
    @Test(priority=2)
    public void createPlaylist()
    {
        int expectedStatusCode=201;
        JSONObject playList = new JSONObject();
        playList.put("name", "Marathi Songs");
        playList.put("description", " song ");
        playList.put("public",false);
        Response response3 = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization",tokenValue)
                .body(playList.toJSONString())
                .pathParam("user_id",userId)
                .when()
                .post("https://api.spotify.com/v1/users/{user_id}/playlists");
        Assert.assertEquals(expectedStatusCode,response3.getStatusCode());
    }
    //To get all playlist based on user Id
    @Test(priority = 3)
    public void getPlaylistBasedOnUserId()
    {
        String expectedPlaylistId1="6RRZJLnaZgCKKIKs9tQVqW";
        String expectedPlaylistId2="5yfSoGzLUcVGbTEAddhduz";
        Response response4=RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization",tokenValue)
                .pathParam("user_id",userId)
                .when()
                .get("https://api.spotify.com/v1/users/{user_id}/playlists");
        int total=response4.path("total");
        playlistId1=response4.path("items[0].id");
        playlistId2=response4.path("items[1].id");
        Assert.assertEquals(expectedPlaylistId1, playlistId1);
        Assert.assertEquals(expectedPlaylistId2, playlistId2);

    }
    //To get all tracks based on playlist id
    @Test(priority = 4)
    public void getAllTracksBasedOnPlaylistId()
    {
        Response response5=RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization",tokenValue)
                .pathParam("playlist_id", playlistId2)
                .when()
                .get("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
                boolean isTrackPresent=response5.body().asString().contains("44gvSP8Kpx6P7VSRHL9dN8");
        Assert.assertEquals(true,isTrackPresent);
    }

    //To delete  playlist Track
    @Test(priority = 5)
    public void deletePlayListTrack()
    {
        int expectedStatusCode=200;
        Response response6=RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization",tokenValue)
                .pathParam("playlist_id", playlistId2)
                .body("{\"uris\": [\"" + "spotify:track:3bZPdEskcnMdepwCaDSJRl" + "\"]}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response6.prettyPrint();
        Assert.assertEquals(expectedStatusCode,response6.getStatusCode());

    }
    // Add track to play list
    @Test(priority = 6)
    public void addPlaylisttrack()
    {
        int expectedStatusCode=201;
        Response response7 = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", tokenValue)
                .pathParam("playlist_id", playlistId2)
                .body("{\"uris\": [\"" + "spotify:track:3bZPdEskcnMdepwCaDSJRl" + "\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response7.prettyPrint();
        Assert.assertEquals(expectedStatusCode,response7.getStatusCode());

    }
    //Modify a playlist details
    @Test(priority = 7)
    public void ChangePlayList()
    {
        Response response8 = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", tokenValue)
                .pathParam("playlist_id", playlistId1)
                .body("{\"name\": \"Marathi Songs \",\"description\": \"New playlist description\",\"public\": true}")
                .when()
                .put("https://api.spotify.com/v1/playlists/{playlist_id}");
        response8.then().assertThat().statusCode(200);
    }
}