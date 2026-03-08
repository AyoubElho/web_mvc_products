# Gestion des Produits Web MVC

Application Spring Boot 3 MVC pour gérer des produits avec une interface Thymeleaf et des endpoints JSON.

## Vue d'ensemble

Ce projet implemente un flux simple de gestion des produits:

- Afficher tous les produits dans un tableau
- Ouvrir un formulaire pour ajouter un produit
- Valider les donnees utilisateur avant l'enregistrement
- Supprimer un produit avec confirmation du navigateur
- Exposer les donnees des produits au format JSON

## Pile Technique

- Java 21
- Spring Boot 3.5.11
- Spring MVC + Thymeleaf
- Spring Data JPA
- Base de donnees H2 en memoire (active dans la configuration actuelle)
- Bootstrap 5.3.8 (via WebJars)
- Interface OpenAPI springdoc

## Apercu de l'Interface

### 1) Page Liste des Produits (`GET /findAll`)

![Page liste des produits](docs/screenshots/01-product-list.png)

Affiche tous les produits (`id`, `name`, `price`, `quantity`), un bouton **Ajouter un produit** et un bouton de suppression par ligne.

### 2) Formulaire d'Ajout Produit (`GET /add`)

![Formulaire d'ajout produit](docs/screenshots/02-product-form.png)

Champs du formulaire:

- `name`
- `price`
- `quantity`

Lors de la soumission, les donnees sont envoyees vers `POST /saveProduct`.

### 3) Confirmation de Suppression (`GET /deleteProduct?id=...`)

![Boite de dialogue de confirmation de suppression](docs/screenshots/03-delete-confirmation.png)

Avant la suppression, l'interface appelle `confirm('est que vous etes sur')` en JavaScript pour demander une confirmation.

### 4) Erreurs de Validation des Champs (`POST /saveProduct`)

![Erreurs de validation du formulaire](docs/screenshots/04-validation-errors.png)

Quand les donnees ne respectent pas les contraintes (`name` entre 4 et 50, `quantity` >= 1), le formulaire est reaffiche avec les messages d'erreur sous les champs concernes.

## Modele de Donnees

Champs et validations de `Product`:

- `id`: `Long`, genere automatiquement (`IDENTITY`)
- `name`: obligatoire, longueur entre 4 et 50
- `price`: minimum `0`
- `quantity`: minimum `1`

## Endpoints

### MVC / Thymeleaf

- `GET /findAll` -> rend `products.html`
- `GET /add` -> rend `form.html`
- `POST /saveProduct` -> valide et enregistre, puis redirige vers `/findAll`
- `GET /deleteProduct?id={id}` -> supprime le produit, puis redirige vers `/findAll`

### JSON

- `GET /findAll/json` -> retourne la liste des produits en JSON
- `GET /products` -> retourne la liste des produits en JSON (controleur REST)

## Execution en Local

### Prerequis

- JDK 21

### Demarrer l'application

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

URL de l'application: `http://localhost:8080`

## Base de Donnees et URLs de Developpement

- Console H2: `http://localhost:8080/h2-console`
- URL JDBC: `jdbc:h2:mem:products-db`
- Swagger UI (springdoc): `http://localhost:8080/swagger-ui/index.html`

## Structure du Projet

```text
src/main/java/org/example/web_mvc_products/
  WebMvcProductsApplication.java
  entity/Product.java
  dao/ProductRepo.java
  ws/ProductController.java
  ws/ProductRestApi.java

src/main/resources/
  application.properties
  templates/products.html
  templates/form.html
```

## Notes

- La configuration actuelle utilise une base H2 en memoire, donc les donnees sont reinitialisees a chaque redemarrage.
- `mysql-connector-j` est present dans les dependances, mais MySQL n'est pas configure actuellement dans `application.properties`.
