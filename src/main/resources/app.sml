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
                label: "Begrüssung"
                src: "welcome.sml"
                duration: "01:08"
            }
            Lection {
                label: "Zielgruppe & Kursziele"
                src: "target.sml"
                duration: "01:39"
            }
            Lection {
                label: "Was ist Compose Desktop?"
                src: "whatis.sml"
                duration: "06:41"
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
                label: "Kommentare"
                src: "kommentare.sml"
                duration: "01:39"
            }
            Lection {
                label: "Funktionen (Teil 1)"
                src: "fun1.sml"
                duration: "01:37"
            }
            Lection {
                label: "Funktionen (Teil 2)"
                src: "fun2.sml"
                duration: "01:43"
            }
            Lection {
                label: "Funktionen (Teil 3)"
                src: "fun3.sml"
                duration: "01:46"
            }
            Lection {
                label: "Bedingungen"
                src: "bedingungen.sml"
                duration: "03:16"
            }
            Lection {
                label: "Loops"
                src: "loops.sml"
                duration: "02:28"
            }
            Lection {
                label: "When"
                src: "when.sml"
                duration: "04:31"
            }
            Lection {
                label: "Datenklassen"
                src: "dataclasses.sml"
                duration: "02:28"
            }
            Lection {
                label: "Klassen"
                src: "classes.sml"
                duration: "03:36"
            }
            Lection {
                label: "Abschluss"
                src: "abschluss.sml"
                duration: "02:39"
            }
        }

        Topic {
            label: "Erste Compose App"

            Lection {
                label: "Einführung"
                src: "einfuehrung.sml"
                duration: "06:41"
            }
            Lection {
                label: "Text Eingabe"
                src: "texteingabe.sml"
                duration: "05:11"
            }
            Lection {
                label: "Layout mit Spalten und Zeilen"
                src: "layout.sml"
                duration: "04:19"
            }
            Lection {
                label: "Dialoge"
                src: "dialoge.sml"
                duration: ""
            }
            Lection {
                label: "Imports"
                src: "imports.sml"
                duration: ""
            }
        }
        Topic {
            label: "Controls"

            Lection {
                label: "Akkordeon"
                src: "akkordeon.sml"
                duration: ""
            }
            Lection {
                label: "Video"
                src: "video.sml"
                duration: ""
            }
            Lection {
                label: "Treeview"
                src: "treeview.sml"
                duration: ""
            }
        }
        Topic {
            label: "Systemintegration & Desktop Features"

            Lection {
                label: "Windows"
                src: "treeview.sml"
                duration: ""
            }
            Lection {
                label: "Mac"
                src: "treeview.sml"
                duration: ""
            }
            Lection {
                label: "Linux"
                src: "treeview.sml"
                duration: ""
            }
        }
        Topic {
            label: "Stile & Themes"
        }
        Topic {
            label: "Todo App"
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
    secondary: "#0DDA17" //"#DDC2A1"
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
