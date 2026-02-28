# Calculator 🧮

A Java calculator built with Swing, featuring basic math operations and three switchable themes: light, dark, and pink.

## Tech

- Java 17+
- Swing
- [exp4j](https://www.objecthunter.net/exp4j/) — for evaluating math expressions

## Features

- Addition, subtraction, multiplication, division and percentage
- `C` to clear everything, `<-` to delete the last character
- Theme switcher via the `@` button at the top

## Themes

| Light | Dark | Pink |
|---|---|---|
| ![](./assets/light_calc.png) | ![](./assets/dark_calc.png) | ![](./assets/pink_calc.png) |

## Running locally

```bash
git clone https://github.com/sofiavitorino/calculator.git
```

Make sure you have Java 17+ installed. The exp4j dependency needs to be on the classpath — if you're using Maven or Gradle, just import it normally.
