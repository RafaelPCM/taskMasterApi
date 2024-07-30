import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testTaskValidation() {
        Task task = new Task();
        task.setTitle(null);
        task.setDescription("Test Description");

        var violations = validator.validate(task);
        assertFalse(violations.isEmpty());

        var titleViolation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("title"))
                .findFirst();

        assertTrue(titleViolation.isPresent());
    }
}
