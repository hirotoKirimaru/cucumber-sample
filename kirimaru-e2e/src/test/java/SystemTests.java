import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class SystemTests {
  @DisabledOnOs(OS.LINUX) // GithubActionsだと動かん…
  @Test
  void test_01() {

    String body =
        """
            {
              "login": "hirotoKirimaru",
              "id": 30658134,
              "node_id": "MDQ6VXNlcjMwNjU4MTM0",
              "avatar_url": "https://avatars3.githubusercontent.com/u/30658134?v=4",
              "gravatar_id": "",
              "url": "https://api.github.com/users/hirotoKirimaru",
              "html_url": "https://github.com/hirotoKirimaru",
              "followers_url": "https://api.github.com/users/hirotoKirimaru/followers",
              "following_url": "https://api.github.com/users/hirotoKirimaru/following{/other_user}",
              "gists_url": "https://api.github.com/users/hirotoKirimaru/gists{/gist_id}",
              "starred_url": "https://api.github.com/users/hirotoKirimaru/starred{/owner}{/repo}",
              "subscriptions_url": "https://api.github.com/users/hirotoKirimaru/subscriptions",
              "organizations_url": "https://api.github.com/users/hirotoKirimaru/orgs",
              "repos_url": "https://api.github.com/users/hirotoKirimaru/repos",
              "events_url": "https://api.github.com/users/hirotoKirimaru/events{/privacy}",
              "received_events_url": "https://api.github.com/users/hirotoKirimaru/received_events",
              "type": "User",
              "site_admin": false,
              "name": "kirimaru",
              "company": null,
              "blog": "https://nainaistar.hatenablog.com/",
              "location": null,
              "email": null,
              "hireable": true,
              "bio": null,
              "twitter_username": "nainaistar",
              "public_repos": 47,
              "public_gists": 0,
              "followers": 5,
              "following": 10,
              "created_at": "2017-08-02T12:38:00Z",
              "updated_at": "2021-01-17T03:15:46Z"
            }
            """;

    given()
        .when()
        .get("https://api.github.com/users/hirotoKirimaru")
        .then()
        .statusCode(200)
        .body("url", equalTo("https://api.github.com/users/hirotoKirimaru"))
//        .body(equalTo(body))

    ;
  }
}
