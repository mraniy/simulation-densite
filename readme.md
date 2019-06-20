L’algorithme du projet est le suivant :
- Lire les points qui sont stockés dans le fichier TSV (static/data.tsv).
- Pour chaque point , déterminer la zone à laquelle il appartient, en résulte un ensemble de zones.
- Ensuite la densité  correspondant à chaque zone est calculée (en utilisant les fonctionnalités d’agrégation de java 8 , le count) ,  une Map<Zone,Densite> est retournée
- ensuite ,  cette map est triée en fonction de la valeur (la densité de la zone) , et enfin les N premieres valeurs en sont retournées.

- Le kata a été entièrement effectué  en TDD , les tests unitaires sont réalisés en gherkin.
- les tests unitaires des composants sont effectués sans mocks.
- J'ai fait le choix de créer une façade entre le service web et les composants, afin de pouvoir mocker facilement les  retours des composants, pour tester l'API rest.
- J'ai essayé de rendre la plupart des objets immuable en utilisant les expressions lambdas de java et en mettant les attributs des objets à final.