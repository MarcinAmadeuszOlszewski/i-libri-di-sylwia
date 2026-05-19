# MVP aplikacji: Nauka włoskiego przez czytanie

## Cel MVP

Zbudować prostą, działającą wersję aplikacji, która umożliwia:

- czytanie tekstu w języku włoskim,
- szybkie tłumaczenie słów i zdań,
- zapis postępu czytania.

MVP ma **zweryfikować kluczową wartość produktu**:

- „Czy użytkownicy faktycznie chcą uczyć się języka przez czytanie z wbudowanym tłumaczem?”

---

## Zakres funkcjonalny (MVP)

### 1. Autentykacja użytkownika

- rejestracja (e-mail + hasło)
- logowanie
- podstawowa sesja użytkownika

---

### 2. Prosta biblioteka tekstów

- lista dostępnych tekstów (np. 3–5 książek / opowiadań)
- teksty w prostym formacie (np. `.txt` lub JSON podzielony na zdania)
- brak uploadu własnych książek (na MVP)

- Źródło: tylko **legalne, darmowe teksty (np. domena publiczna)**

---

### 3. Widok czytania (core feature)

- tekst podzielony na **zdania (zdanie pod zdaniem)**
- każde zdanie:
    - podzielone na słowa (tokenizacja)
- interakcje:
    - kliknięcie słowa → tłumaczenie słowa
    - kliknięcie zdania → tłumaczenie zdania

- UI może być prosty:
- tekst po lewej
- tooltip / panel z tłumaczeniem po kliknięciu

---

### 4. Tłumaczenia (API)

- jedno API do wszystkiego (na MVP):
    - np. **DeepL API** lub **Google Translate API**
- zapis tłumaczeń w BD (cache) → redukcja kosztów i szybsze odpowiedzi

---

### 5. Zapis postępu

- zapis:
    - ostatnie przeczytane zdanie
    - aktualna książka
- po wejściu użytkownik może kliknąć „Kontynuuj”

---

## Co świadomie NIE wchodzi do MVP

Aby utrzymać prostotę:

- bez fiszki / nauka słownictwa
- bez ulubione słowa
- bez import własnych książek
- bez wiele języków
- bez audio / wymowa
- bez zaawansowany cache tłumaczeń
- bez rekomendacje książek

---

## Proponowana architektura (prosta)

### Frontend

- Web app (React / Next.js)
- widoki:
    - login/register
    - lista książek
    - czytnik

---

### Backend

- proste API (Java + Spring Boot)
- endpointy:
    - `/auth`
    - `/books`
    - `/progress`
    - `/translate` (proxy do API tłumaczeń)

---

### Baza danych

Minimalne tabele:

- `users`
- `books`
- `progress`

---

### Integracje

- 1 API tłumaczeń (np. DeepL/Google)

---

## Metryki sukcesu MVP

Na co patrzeć:

- % użytkowników, którzy:
    - klikają tłumaczenia
    - wracają do czytania (retencja)
- średni czas sesji
- liczba przeczytanych zdań / sesję
- liczba kliknięć tłumaczeń

- Jeśli użytkownicy aktywnie korzystają z tłumaczeń → pomysł działa

---

## Plan realizacji (prosty)

### Etap 1 (1–2 tygodnie)

- auth + baza danych
- statyczne teksty książek

### Etap 2 (1 tydzień)

- widok czytania + kliknięcia
- integracja tłumaczeń

### Etap 3 (1 tydzień)

- zapis postępu
- UX poprawki

---

## Następny krok po MVP

Jeśli MVP zadziała:

1. fiszki (największa wartość edukacyjna)
2. lepszy reader (UX + mobile)
3. import własnych tekstów (jeśli legalność ogarnięta)

---

## Kluczowa decyzja MVP

**Najważniejsze: nie komplikować.**

MVP powinno odpowiedzieć na jedno pytanie:
> Czy ludzie chcą czytać książki z wbudowanym tłumaczeniem i czy to im pomaga?

