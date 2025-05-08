App {
    name: "DemoWorkshop"
    id: "com.sample.app.DemoWorkshop"
    version: "1.0.0"
    description: "This has to be filled."
    author: "Adam Art Ananda"


    Course {
        lang: "de"
        name: "Demo Workshop"
        description: "This is the description"

        Topic {
            label: "Topic 1"

            Lection {
                label: "Lection 1"
                link: "http://youtube.com/lection_1"
            }

            Lection {
                label: "Lection 2"
                src: "lection_2.md"
            }
            Lection {
                label: "Lection 3"
                src: "lection_3.md"
            }
        }

        Topic {
            label: "Topic 2"

            Lection {
                label: "Lection 4"
                src: "lection_4.md"
            }

            Lection {
                label: "Lection 5"
                src: "lection_5.md"
            }
        }
        Topic {
            label: "Topic 3"

            Lection {
                label: "Lection 6"
                src: "lection_6.md"
            }

            Lection {
                label: "Lection 7"
                src: "lection_7.md"
            }
        }
    }

    Theme {
    primary: "#FFB951"
    onPrimary: "#452B00"
    primaryContainer: "#633F00"
    onPrimaryContainer: "#FFDDB3"
    secondary: "#DDC2A1"
    onSecondary: "#3E2D16"
    secondaryContainer: "#56442A"
    onSecondaryContainer: "#FBDEBC"
    tertiary: "#B8CEA1"
    onTertiary: "#243515"
    tertiaryContainer: "#3A4C2A"
    onTertiaryContainer: "#D4EABB"
    error: "#FFB4AB"
    errorContainer: "#93000A"
    onError: "#690005"
    onErrorContainer: "#FFDAD6"
    background: "#1F1B16"
    onBackground: "#EAE1D9"
    surface: "#1F1B16"
    onSurface: "#EAE1D9"
    surfaceVariant: "#4F4539"
    onSurfaceVariant: "#D3C4B4"
    outline: "#9C8F80"
    inverseOnSurface: "#1F1B16"
    inverseSurface: "#EAE1D9"
    inversePrimary: "#825500"
    surfaceTint: "#FFB951"
    outlineVariant: "#4F4539"
    scrim: "#000000"
  }
}
