
# SwingEase UI Library

SwingEase is a modern, fluent Java Swing UI library 
that makes creating beautiful user interfaces easy and intuitive. 
It provides a collection of pre-built components and layouts 
with a chainable API inspired by Flutter.


## Features

- 🎨 Modern UI components with customizable styles
- 📐 Flexible layout system with MigLayout
- ⚡ Fluent API design
- 🎯 Easy positioning and sizing
- 🖌️ Custom styling support with gradients
- 📦 Modern components with rounded corners

## Dependencies

Add these dependencies to your project:

### Gradle (Kotlin DSL)
``` kotlin
dependencies { 
   implementation("com.formdev:flatlaf:3.4") 
   implementation("com.miglayout:miglayout-swing:11.1") 
   // Placeholder for the dependency
}
``` 

### Gradle (Groovy)
```groovy 
dependencies { 
    implementation 'com.formdev:flatlaf:3.4' 
    implementation 'com.miglayout:miglayout-swing:11.1'
    // Placeholder for the dependency
}
``` 

### Maven
```xml
<dependencies>
    <dependency>
        <groupid>com.formdev</groupid>
        <artifactid>flatlaf</artifactid>
        <version>3.4</version>
    </dependency>

    <dependency>
        <groupid>com.miglayout</groupid>
        <artifactid>miglayout-swing</artifactid>
        <version>11.1</version>
    </dependency>

    <!-- 
    Placeholder for the dependency 
    <dependency>
        <groupid>de.dragonrex</groupid>
        <artifactid>swing-lite</artifactid>
        <version>1.0.0</version>
    </dependency>
    -->
</dependencies>
``` 

## Quick Start

Here's a simple example of creating a login form:
```java
import com.formdev.flatlaf.FlatLightLaf;
import de.dragonrex.components.MButton;
import de.dragonrex.components.TextField;
import de.dragonrex.layout.Row;
import de.dragonrex.math.Size;
import de.dragonrex.UI;
import de.dragonrex.components.Text;
import de.dragonrex.layout.Column;
import de.dragonrex.style.Padding;

public class Main {
    public static void main(String[] args) {
        GradientPaint gp = new GradientPaint(0, 0, new Color(66, 133, 244),
                100, 40, new Color(52, 168, 83));
        UI.build(
                "",
                Size.of(800, 600),
                new FlatLightLaf(),
                Column.of(
                        Padding.of(10, 50, 10, 10,
                                Text.of("Username")
                                        .fontSize(14).bold()
                                        .size(200, 30)
                        ),
                        Padding.of(0, 10, 10, 10,
                                TextField.of()
                                        .initialText("z.B. Max Mustermann")
                                        .columns(1)
                                        .size(300, 40)
                        ),
                        Row.of(
                                Padding.of(0, 10, 10, 5,
                                        MButton.of("Login")
                                                .onClick(() -> System.out.println("Login clicked"))
                                                .size(100, 40)
                                ),
                                Padding.of(0, 5, 10, 10,
                                        MButton.of("Cancel")
                                                .onClick(() -> System.out.println("Login canceled"))
                                                .size(100, 40)
                                )
                        )
                )
        );
    }
}
```


## Styling Properties

All components support these basic styling properties:
- `size(width, height)`: Set component dimensions
- `backgroundColor(Color)`: Set background color
- `foregroundColor(Color)`: Set text/foreground color
- `margin(top, left, bottom, right)`: Set margins
- `visible(boolean)`: Set visibility
- `position(x, y)`: Set absolute position

Modern components (MButton, Container) additionally support:
- `radius(int)`: Set corner radius
- `gradient(Color, Color)`: Set gradient background

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Support

If you have any questions or need help, please open an issue in the GitHub repository.

