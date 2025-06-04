App {
    id: "at.crowdware.course.ComposeDesktop"
    version: "1.0.0"
    description: "This has to be filled."
    author: "Adam Art Ananda"

    Course {
        lang: "de"
        name: "Compose Desktop"
        description: "This is the description"

        Topic {
            label: "Einleitung"

            Lection {
                label: "Willkommen"
                src: "welcome.sml"
                duration: "1:34"
            }
            Lection {
                label: "Zielgruppe & Kursziele"
                src: "target.sml"
                duration: "2:10"
            }
            Lection {
                label: "Was ist Compose Desktop?"
                src: "whatis.sml"
                duration: "1:20"
            }
            Lection {
                label: "Projekt aufsetzen mit IntelliJ + Gradle"
                src: "setup.sml"
                duration: "1:20"
            }
            Lection {
                label: "Erste App"
                src: "firstapp.sml"
                duration: "1:20"
            }
        }

        Topic {
            label: "Kotlin Grundlagen"

            Lection {
                label: "Variablen"
                src: "vars.sml"
                duration: "03:02"
            }

            Lection {
                label: "Ausdrücke"
                src: "expressions.sml"
                duration: "02:32"
            }
            Lection {
                label: "Funktionen"
                src: "fun1.sml"
                duration: "01:37"
            }
            Lection {
                label: "Funktionen2"
                src: "fun2.sml"
                duration: ""
            }
            Lection {
                label: "Funktionen3"
                src: "fun3.sml"
                duration: ""
            }
            Lection {
                label: "Bedingungen"
                src: "loops.sml"
                duration: ""
            }
            Lection {
                label: "Switches"
                src: "when.sml"
                duration: ""
            }
            Lection {
                label: "Klassen, Datenklassen, Sealed Classes"
                src: "classes.sml"
                duration: ""
            }

            Lection {
                label: "Extensions & Top-Level-Funktionen"
                src: "extensions.sml"
                duration: ""
            }
        }

        Topic {
            label: "Erste App"

            Lection {
                label: "Lection 6"
                src: "lection_6.sml"
                duration: "5:30"
            }

            Lection {
                label: "Lection 7"
                src: "lection_7.sml"
                duration: "8:20"
            }
        }

        Topic {
            label: "Layouts und Navigation"
        }

        Topic {
            label: "Systemintegration & Desktop Features"
        }

        Topic {
            label: "Stil & Themes"
        }

        Topic {
            label: "Zweite App"
        }

        Topic {
            label: "Verteilung & Packetierung"
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
