# Aplikacija "Sigurnost na Internetu" – REST API

Ova Java Spring Boot aplikacija omogućava upravljanje pitanjima i odgovorima vezanima za sigurnost na internetu.  
Aplikacija koristi Hibernate ORM za rad s bazom podataka te nudi REST API endpointove za unos, dohvat i upravljanje pitanjima i odgovorima.  
Dodatno, aplikacija je dokumentirana pomoću Swagger UI sučelja.

---

## Modeli (Entiteti)

### `Pitanje`

- **sifra** (Integer) — Primarni ključ (automatski generiran)
- **tekst** (String) — Tekst pitanja

### `Odgovor`

- **sifra** (Integer) — Primarni ključ (automatski generiran)
- **tekst** (String) — Tekst odgovora
- **jeTocan** (Boolean) — Oznaka je li odgovor točan
- **pitanje** (ManyToOne Pitanje) — Veza na entitet Pitanje

---

## DTO klase

- **PitanjeDTO**

  - `tekst` (String)

- **OdgovorDTO**
  - `sifraPitanja` (Integer)
  - `tekst` (String)
  - `jeTocan` (Boolean)

---

## Servis metode

### `PitanjeService`

- `List<Pitanje> get()` — Dohvaća sva pitanja
- `Pitanje post(PitanjeDTO dto)` — Sprema novo pitanje
- `List<Odgovor> getOdgovore(int sifraPitanja)` — Dohvaća sve odgovore za određeno pitanje

### `OdgovorService`

- `List<Odgovor> get()` — Dohvaća sve odgovore
- `Odgovor post(OdgovorDTO dto)` — Sprema novi odgovor

---

## API Endpointi

Svi endpointi dostupni su pod prefiksom:  
`/api/dgreger/`

### `PitanjeController`

- `GET /api/dgreger/pitanje`  
  Dohvaća sva pitanja

- `POST /api/dgreger/pitanje/post`  
  Unosi novo pitanje

- `GET /api/dgreger/pitanje/{sifra}/odgovori`  
  Dohvaća sve odgovore vezane uz pitanje

### `OdgovorController`

- `GET /api/dgreger/odgovor`  
  Dohvaća sve odgovore

- `POST /api/dgreger/odgovor/post`  
  Unosi novi odgovor

---

## Swagger UI

Za pregled i testiranje svih endpointova koristi:  
`http://localhost:8080/swagger-ui/index.html`

---

## Baza podataka

Aplikacija koristi **MariaDB**.  
Primjeri tablica:

- `pitanje`
- `odgovor` (sa stranim ključem `pitanje_sifra`)

---

## Napomena

Za pokretanje aplikacije osigurati:

- Da nijedna druga aplikacija ne koristi port 8080 ili promijeniti port u `application.properties`
- Da su ispravne postavke za bazu podataka

---

### Uvoz dump-a baze podataka (DBeaver)

Pokreni "DBeaver" i spoji se na svoju "MariaDB/MySQL" instancu.

Desni klik na databases → Create new database (ako baza još nije kreirana).

Odaberi bazu na koju želiš importirati podatke.

Desni klik na bazu → Tools → Execute script.

Odaberi svoj `.sql` dump file s diska.

Klikni Execute (ili pritisni Ctrl+Enter) za izvođenje dump-a.

Provjeri tablice u bazi — trebale bi biti popunjene.

---

### Uvod dump-a baze podataka (PhpMyAdmin)

Otvori "phpMyAdmin" u pregledniku.

Na lijevoj strani odaberi bazu u koju želiš importirati dump.

Ako baza ne postoji, kreiraj novu pod nazivom iz dump-a.

Klikni na Import tab.

Odaberi dump `.sql` datoteku sa svog računala.

Podesi character set of the file na utf8 (ako dump koristi UTF-8).

Klikni na Go.

Nakon završetka procesa, provjeri popis tablica — podaci bi trebali biti učitani.
