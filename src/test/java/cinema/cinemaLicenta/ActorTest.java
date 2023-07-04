package cinema.cinemaLicenta;

import cinema.cinemaLicenta.entity.Actor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActorTest {

    @Test
    public void testActorAttributes() {
        // Create an instance of Actor
        Actor actor = Actor.builder()
                .id(1L)
                .first_name("John")
                .last_name("Doe")
                .build();

        // Test the attribute values using assertions
        assertThat(actor.getId()).isEqualTo(1L);
        assertThat(actor.getFirst_name()).isEqualTo("John");
        assertThat(actor.getLast_name()).isEqualTo("Doe");
    }

    @Test
    public void testActorSetters() {
        // Create an instance of Actor
        Actor actor = new Actor();

        // Set attribute values using setters
        actor.setId(2L);
        actor.setFirst_name("Jane");
        actor.setLast_name("Smith");

        // Test the updated attribute values using assertions
        assertThat(actor.getId()).isEqualTo(2L);
        assertThat(actor.getFirst_name()).isEqualTo("Jane");
        assertThat(actor.getLast_name()).isEqualTo("Smith");
    }
}
