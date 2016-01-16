# Hackathon Nec Mergitur

## Sujet : 

**Défi 672 : Comment aider à la décision, temps réel pour l’orientation des victimes, vers les structures hospitalières ?**

*Porteur : Dr Eric Lecarpentier, APHP*

Lors d’évènements majeurs les victimes sont évacuées rapidement vers les établissements hospitaliers. Les évacuations sont décidées par les médecins régulateurs et évacuateurs du SAMU situés au plus près des victimes. Plusieurs médecins, répartis sur le terrain, peuvent décider des orientations. L’objectif du défi est d’apporter aux décideurs, en temps réel, une synthèse de l’ensemble des décisions prises sur le terrain, consolidée des informations collectées à l’arrivée des établissements hospitaliers.

## Objectif : 
Dispatcher au mieux les patients à partir du terrain dans un delta "t" très court tout en permettant une synthèse au centre de régulation.
Il est nécessaire de savoir combien de victimes ont été envoyées en UA (Urgence Absolue) ou en UR (Urgence Relative) vers les hôpitaux.
Il faut donc utiliser des jauges qui vont permettre de connaître les disponibilités des différents établisements qui auront été ouverts pour l'occasion. 
** Si la jauge de départ est incertaine, celle de l'arrivée à l'hôpital est sûre.
Les UA peuvent toujhours être requalifiées à tout instant en UR et vice versa.

Les données de base des capacités des hôpitaux à t0 sont déjà paramétrés. 

Géolocalisation des médecins avec leur ID

Capacité à gérer plusieurs sites.

Les non badgés sont purgés au bout de trois quart d'heure

Rappel de la réactualisation de la jauge de Reveil par différents moyens (alerte sonore, notification, etc.). Ce rappel doit être paramétrable sur le plan de la périodicité

La présentation des hôpitaux dans la liste est priorisée en fonction de la répartition de la charge et de la proximité.



### Eléments de Pitch
Pas de matériel spécifique. on travaille avec du matériel existant. Bracelet actuels / 
