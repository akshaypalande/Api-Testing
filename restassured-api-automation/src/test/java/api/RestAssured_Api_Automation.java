package api;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssured_Api_Automation {

	String token;
	String userId;
	String playlistId;

	@BeforeTest
	public void Get_Token() {
		token = "Bearer BQDuhTEnUuqtru_z9hmVkM7gdyp0R4PDFnZFAidF__CMEw1p4BKUEWVf3qKdsJQgSH-5JK4MQlgKPADdYsi9dTQFcaQ4s4bUEW6dAuDYIzdZqMNNNQiCRPeKJhpGN63kq7YEst8qi4Slie3OWjMIzdOhsdt6qdmQjYtvrH77s5R2LOFG4DJqofiKBz6rnOTGU0vL10Mf-Zi6l6VpC9pjTe8NZD61wwNGs5zeDjL5h1GPk2CkxXx5CmJap9nGb4c2fA2idFk";
	}

	// UserProfile
	@Test(priority = 1)
	public void get_Current_UserProfile() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		userId = response.path("id");
		System.out.println("UserId :" + userId);
		System.out.println("------------------------------------------------");
		//response.then().assertThat().statusCode(200);
		Assert.assertEquals(200, response.getStatusCode());

	}
	
	
	
	@Test(priority = 2)
	
	public void get_UserProfile() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userId);
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        //response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }
	
	
	//Search
		@Test(priority = 3)//,enabled = false)
		
		public void search_for_Item() {
			Response response = RestAssured.given()
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("Authorization", token).when()
					//.pathParam("q", "B praak")
					//.pathParam("type", "track")
					.queryParam("q", "B praak")
					.queryParam("type", "track")
					.get("https://api.spotify.com/v1/search");
			response.prettyPrint();
		}

	
	// Playlist
    @Test(priority = 4)
    public void playlist_Creation() {
        Response response =RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\r\n"
                		+ "  \"name\": \"New Playlist_04\",\r\n"
                		+ "  \"description\": \"New playlist Bpraak\",\r\n"
                		+ "  \"public\": false\r\n"
                		+ "}")
                .when()
                .post("https://api.spotify.com/v1/users/" + userId + "/playlists");
        response.prettyPrint();
        playlistId = response.path("id");
        System.out.println("Playlist Id :" + playlistId);
        response.then().assertThat().statusCode(201);
        //Assert.assertEquals(201, response.getStatusCode());
    }
    @Test(priority = 5)
    public void playlist_NewAdd_Item(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .queryParam("uris","spotify:track:6N4TjPeZi4S9jpayOpRX2h,spotify:track:4cFwhwH5ydnHgB0Tv3lZsm" )
                .when()
                .post("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(201, response.getStatusCode());
    }
    
    @Test(priority = 6)
    public void playlist_Get_Playlist(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId+"/");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test(priority = 7)
    public void playlist_Get_CurrentUser_Playlist(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
        
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    

    @Test(priority = 8)
    public void playlist_Get_Cover_Image(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId+"/images");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test(priority = 9)
    public void playlist_Get_Playlist_Item(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        int total_playlist_item=response.path("total");
        System.out.println("Total Playlist item :"+total_playlist_item);
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test(priority = 10)
    public void playlist_Get_User_Playlist(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId+"/playlists");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test(priority = 11)
    public void playlist_Update_Item(){
    	Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .body("{\"range_start\":2,\"insert_before\":5,\"range_length\":3}")
                .queryParam("uris","spotify:track:7DE0I3buHcns00C0YEsYsY" )
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(201,response.getStatusCode());
        
    }
    @Test(priority = 12)
    public void playlist_Update_Playlist_Detail(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .body("{\r\n"
                		+ "  \"name\": \"New Playlist_0404\",\r\n"
                		+ "  \"description\": \" Bpraak Song\",\r\n"
                		+ "  \"public\": false\r\n"
                		+ "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistId+"/");
        response.prettyPrint();
        //response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test(priority = 13)
    public void playlist_Delete_Item(){
        Response response=RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .body("{\r\n"
                		+ "\"tracks\": [{\"uri\":\"spotify:track:7DE0I3buHcns00C0YEsYsY\"}]\r\n"
                		+ "}")
                .when()
                .delete("	https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
        //response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    

}
    
 // Tracks
    @Test
    public void tracks_Get_Tracks_Audio_Analysis(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/7D3lb2gVq6luDQ5kPUX0Pi");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void tracks_Get_Tracks_Audio_Features(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","7DE0I3buHcns00C0YEsYsY,3czhw7W0PTAIROkL1tcsKX")
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids={ids}");
                 response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void tracks_Get_Trackss_Audio_Features(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","7DE0I3buHcns00C0YEsYsY")
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void tracks_Get_Several_Tracks(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","7DE0I3buHcns00C0YEsYsY,3czhw7W0PTAIROkL1tcsKX")
                .when()
                .get("https://api.spotify.com/v1/tracks?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void tracks_Get_Track(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","3czhw7W0PTAIROkL1tcsKX")
                .when()
                .get("https://api.spotify.com/v1/tracks?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    // Albums
    @Test
    public void albums_Get_Album_Tracks(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("id","29MhBXO988c8iFL7TZK17R")
                .when()
                .get("https://api.spotify.com/v1/albums/{id}/tracks");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void albums_Get_Album(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("id","29MhBXO988c8iFL7TZK17R")
                .when()
                .get("https://api.spotify.com/v1/albums/{id}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void albums_Get_Several_Albums(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","29MhBXO988c8iFL7TZK17R,224jZ4sUX7OhAuMwaxp86S")
                .when()
                .get("https://api.spotify.com/v1/albums?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
 // Markets
    @Test
    public void markets_Get_Available_Markets(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    // Shows
    @Test
    public void shows_Get_Show(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                //.pathParam("id","0ZQqqOIyZQZsbuPCvEa9kK")
                .queryParam("ids","0ZQqqOIyZQZsbuPCvEa9kK")
                .when()
                .get("https://api.spotify.com/v1/shows");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void shows_Get_Show_Episodes(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("id","0ZQqqOIyZQZsbuPCvEa9kK")
                .when()
                .get("https://api.spotify.com/v1/shows/{id}/episodes");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void shows_Get_Several_Shows(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","0ZQqqOIyZQZsbuPCvEa9kK,3jFtwBciHeEPlNF5Gf411T")
                .when()
                .get("https://api.spotify.com/v1/shows?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
  //Episode
    @Test
    public void episode_Get_Episode() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .pathParam("id","4yF8eAxFWtwnzuFnMA6UOQ")
                .when()
                .get("https://api.spotify.com/v1/episodes/{id}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }
    
    @Test
    public void episodes_Get_Several_Episode() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .pathParam("ids","4yF8eAxFWtwnzuFnMA6UOQ,1mVoaGA4lw93lLNBDIQ3Dl")
                .when()
                .get("https://api.spotify.com/v1/episodes?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }
    
 // Browse
    @Test
    public void browse_Get_Available_Genre_Seeds(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void browse_Get_Several_Browse_Categories(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void get_Single_Browse_Category(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("category_id","0JQ5DAqbMKFHCxg5H5PtqW")
                .when()
                .get("https://api.spotify.com/v1/browse/categories/{category_id}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void get_Categorys_Playlists(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("category_id","0JQ5DAqbMKFHCxg5H5PtqW")
                .when()
                .get("https://api.spotify.com/v1/browse/categories/{category_id}/playlists");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void browse_Get_Featured_Playlists(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void browse_Get_New_Releases(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void browse_Get_Recommendations(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("seed_artists","0tC995Rfn9k2l7nqgCZsV7")
                .pathParam("seed_genres","classical,country")
                .pathParam("seed_tracks","33d0aCkd8fJUtnLTNLDOxV")

                .when()
                .get("https://api.spotify.com/v1/recommendations?seed_artists={seed_artists}&seed_genres={seed_genres}&seed_tracks={seed_tracks}");

        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
 // Artists
    @Test
    public void artists_Get_Artists_Albums(){
        Response response =RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("id","2LMljlTXjGr7jPZOBmK9My")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}/albums");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void artists_Get_Artists_Related_Artists(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("id","2LMljlTXjGr7jPZOBmK9My")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}/related-artists");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
    @Test
    public void artists_Get_Artist(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("id","2LMljlTXjGr7jPZOBmK9My")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void artists_Get_Several_Artists(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("ids","2LMljlTXjGr7jPZOBmK9My,0mV4UQ0gHg59AAUtg968pX")
                .when()
                .get("https://api.spotify.com/v1/artists?ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
 // Follow
    @Test
    public void follow_Follows_Artists_or_Users(){
        Response response = RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",token)
                .pathParam("type","artist")
                .pathParam("ids","2LMljlTXjGr7jPZOBmK9My,0mV4UQ0gHg59AAUtg968pX")
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type={type}&ids={ids}");
        response.prettyPrint();
        System.out.println("------------------------------------------------");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    
 
}