package za.co.prog6112.q3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class ProductFileServiceTest {
    @TempDir Path tempDir;

    @Test void savesLoadsAndFindsProductById() throws Exception {
        ProductFileService service = new ProductFileService(tempDir.resolve("Products.txt"));
        service.appendProduct(new Product("A100", "Example Shoes", 499.99));

        assertEquals(1, service.loadProducts().size());
        Product found = service.findById("A100");
        assertNotNull(found);
        assertEquals("Example Shoes", found.getName());
        assertEquals(499.99, found.getPrice(), 0.001);
    }

    @Test void missingProductReturnsNull() throws Exception {
        ProductFileService service = new ProductFileService(tempDir.resolve("Products.txt"));
        assertNull(service.findById("missing"));
    }
}
