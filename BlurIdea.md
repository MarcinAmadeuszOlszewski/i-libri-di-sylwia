# Pomysł na aplikację: Nauka języka włoskiego poprzez czytanie

## Opis ogólny
Aplikacja wspiera naukę języka włoskiego poprzez czytanie książek w oryginalnym języku. Użytkownik ma możliwość interaktywnego poznawania znaczenia słów i zdań bez opuszczania tekstu, co pozwala utrzymać ciągłość czytania i zwiększa efektywność nauki.

---

## Główne założenia
- Czytanie autentycznych tekstów w języku włoskim
- Możliwość szybkiego sprawdzania tłumaczeń w kontekście
- Zachowanie płynności czytania bez konieczności korzystania z zewnętrznych narzędzi
- Personalizacja doświadczenia poprzez zapis postępów

---

## Sposób działania

1. **Logowanie**
    - użytkownik zakłada konto i loguje się (e-mail + hasło)

2. **Wybór książki**
    - użytkownik wybiera książkę z dostępnej biblioteki

3. **Tryb czytania**
    - tekst książki prezentowany jest w sposób czytelny (zdanie pod zdaniem)
    - użytkownik może:
        - kliknąć słowo, aby zobaczyć jego tłumaczenie
        - kliknąć zdanie, aby zobaczyć tłumaczenie całego zdania

4. **Zapis postępu**
    - aplikacja zapamiętuje ostatnie miejsce czytania
    - użytkownik może wrócić do lektury w dowolnym momencie

---

## Funkcjonalności dodatkowe

- biblioteka użytkownika:
    - lista dostępnych książek
    - lista książek aktualnie czytanych
    - lista książek przeczytanych

- możliwość dodawania własnych tekstów przez użytkownika (opcjonalne, z uwzględnieniem ograniczeń prawnych i technicznych)

---

## Wątpliwości techniczne

### Źródła tekstów
- konieczność zapewnienia zgodności z prawem autorskim
- wybór formatu (np. pliki tekstowe, epub, inne formaty e-booków)
- potencjalne wykorzystanie tekstów z domeny publicznej

---

### Tłumaczenia słów

Możliwe podejścia:
- integracja z API słowników (np. PONS lub inne dostępne rozwiązania)
- wykorzystanie API tłumaczeń:
    - Google Translate API (szeroki zakres języków, płatne)
    - DeepL API (wysoka jakość, darmowy plan z limitami)
    - modele AI udostępniane przez API

Wyzwanie:
- tłumaczenie pojedynczych słów bez kontekstu może być niedokładne

---

### Tłumaczenia zdań

Możliwe rozwiązania:
- Google Translate API
- DeepL API
- modele AI

Decyzja do podjęcia:
- tłumaczenie w czasie rzeczywistym vs przechowywanie tłumaczeń

---

### Przechowywanie tłumaczeń

- słowa:
    - możliwość budowy własnego słownika poprzez zapisywanie tłumaczeń
- zdania:
    - mniejsza powtarzalność, więc potencjalnie lepiej tłumaczyć na bieżąco
    - caching może być uzasadniony w obrębie jednej książki

---

## Forma aplikacji

### Aplikacja webowa
- dostęp z poziomu przeglądarki
- brak konieczności instalacji
- szybsze wdrożenie

### Aplikacja mobilna
- wygodniejsze czytanie na urządzeniach mobilnych
- większa złożoność implementacji
- potencjalnie lepsze dopasowanie do docelowego sposobu użycia

---

## Dalszy rozwój

- system fiszek oparty na klikanych słowach
- możliwość oznaczania słów jako ulubione
- rozszerzenie o inne języki
- obsługa importu własnych książek (z uwzględnieniem kwestii prawnych i technicznych)
- funkcje audio:
    - odsłuch wymowy słów
    - odsłuch całych zdań

---

## Podsumowanie

Aplikacja ma na celu połączenie czytania i nauki języka w jednym narzędziu, eliminując potrzebę korzystania z dodatkowych słowników i translatorów. Kluczowym elementem jest szybki dostęp do tłumaczeń w kontekście oraz zachowanie płynności czytania.