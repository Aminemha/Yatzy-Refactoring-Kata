# Yatzy-Refactoring-Kata - Amine MHAMED

Résumé des modifications:
- Les méthodes statiques ont été supprimées et déplacées en méthodes d'instance.
- Une validation a été ajoutée pour vérifier que les valeurs des dés sont comprises entre 1 et 6 lors de la création de l'objet Yatzy.
- Les méthodes ont été renommées pour plus de clarté.
- Des méthodes privée ont été ajoutées pour plus de maintenabilité.
- Les variables et constantes ont été renommées pour plus de lisibilité. 
- La documentation a été mise à jour pour refléter les changements apportés.
- Des cas de test ont été ajoutées pour avoir un coverage à 100%
- Réorganisation des tests unitaires en utilisant les annotations ParameterizedTest et MethodSource.
- Ajout d'un test pour vérifier le lancement d'une exception IllegalArgumentException avec une valeur de dé invalide.
- Commentaires et formatage du code pour améliorer la lisibilité.
