# Estiam App - Application Android

## Fonctionnalités implémentées

### 1. Authentification Firebase (Email/Password) 

- Écrans Login et Register avec validation UI en temps réel
- Validation du format email 
- Validation mot de passe ≥ 6 caractères avec messages d'erreur
- Messages d'erreur affichés en temps réel sous les champs
- Logout fonctionnel
- Guard d'accès (RequireAuth) implémenté
- Redirection automatique vers Login si non connecté
- Redirection automatique vers l'écran Films après connexion/inscription

### 2. Navigation & UI 

- 4 écrans principaux : Home, Films, Users, Settings
- Scaffold avec TopBar et SnackbarHost
- Bouton Toast fonctionnel
- Bouton Snackbar avec action fonctionnel
- Previews multiples : 2 previews par écran (Phone Light, Phone Dark)

### 3. Localisation

- res/values/strings.xml (FR par défaut)
- res/values-fr/strings.xml (FR)
- res/values-en/strings.xml (EN)


### 5. Firestore 

- Intégration avec Firebase Authentication pour l'identification des utilisateurs

### 6. API Réseau

- Retrofit configuré avec OkHttp
- OkHttp logging interceptor pour le débogage
- Écran liste depuis API publique :
  - TMDB API pour les films populaires
  - DummyJSON API pour les utilisateurs et produits
- Pull-to-Refresh implémenté sur tous les écrans de liste
- Pagination au scroll 
- Gestion explicite de l'état "empty" avec message et bouton de rafraîchissement

### 8. WorkManager 

- OneTime WorkRequest avec délai de 10 secondes → notification "Tâche exécutée !"
- NotifyWorker implémenté pour l'envoi de notifications

### 10. Tests unitaires 

- ValidationTest.kt : Tests de validation email et password
  - Validation du format email
  - Validation de la longueur du mot de passe
  - Tests des fonctions de validation
- MoviesViewModelTest.kt : Tests pour MoviesViewModel
  - Tests de l'état initial
  - Tests de la propriété isEmpty
  - Tests de la fonction loadMore
- AuthViewModelTest.kt : Tests pour AuthViewModel
  - Structure de test pour l'authentification
- Dépendances de test ajoutées : Mockito, Mockito Kotlin, Coroutines Test
