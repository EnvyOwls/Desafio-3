# Desafio Modulo 3: Manejo de datos espaciales
## Equipo Rocket


 A partir de un set de datos recopilados en un CSV, se obtuvo la información de aplicaciones con caracteristicas similares a las que se encuentra desarrollando. Dicho set se encuentra almacenado en el siguiente orden:
 
###### Glosario de atributos
- "id" : App ID
- "track_name": Nombre de la App
- "size_bytes": Tamaño (en Bytes)
- "currency": Moneda
- "price": Precio en la moneda
- "ratingcounttot": Cantidad de reseñas (Para todas las versiones)
- "ratingcountver": Cantidad de reseñas (Para la versión actual)
- "user_rating" : Promedio de puntaje de las reseñas (Para todas las versiones)
- "userratingver": Promedio de puntaje de las reseñas (Para la versión actual)
- "ver" : Ultima version
- "cont_rating": Rating de contenido
- "prime_genre": Género principal
- "sup_devices.num": Cantidad de dispositivos soportados
- "ipadSc_urls.num": Cantidad de capturas de pantallas por dispositivos
- "lang.num": Cantidad de lenguajes soportados
- "vpp_lic": Licencia Vpp activada

Se desea encontrar:
1. Una aplicación en específico mediante su respectiva id.
2. 10 aplicaciones similares a una aplicacion dada mediante su id.
3. Mostrar información de las 10 aplicaciones más parecidas a vector de atributos.

Para el desarrollo de la solución se propuso un KDTree, el cual haciendo uso de la distancia euclidiana ordena los KDNodes. Estos nodos poseen un arreglo con las caracteristicas que se pueden comparar, ademas de atributos con el resto de características.

~~~
KDNode{

    private double[] caracteristicas_comparables;
    int id;
    private String track_name;
    private String currency;
    private String ver;
    private int ipadSc_urls_num;
    private int vpp_lic;
    
    KDNode Parent;
    KDNode Left;
    KDNode Right;
    
}
~~~


Para una mejor ejecución de la solución, se provee de un ejecutable.
Para acceder a él se debe dirigir a la ubicación en donde se encuentra.

1. Para ver informacion sobre una aplicacion especifica, ingresar: 

~~~
find [ id ]
~~~

2. Para ver las aplicaciones mas parecidas a una mediante su id, ingresar: 

~~~
similar [id]
~~~

3. Para ver las aplicaciones similares a una mediante un vector:

~~~
similar [size_bytes] [price] [rating_count_tot] [rating_count_ver] [user_rating] [user_rating_ver] [cont_rating] [prime_genre] [sup_devices_num] [lang_num]
~~~


Desde el ejecutable también es posible acceder a ayuda:

~~~
java -jar ejecutable.java -help
~~~



