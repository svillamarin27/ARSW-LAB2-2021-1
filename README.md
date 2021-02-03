
### Escuela Colombiana de Ingeniería

### Arquitecturas de Software – ARSW
## Laboratorio Programación concurrente, condiciones de carrera, esquemas de sincronización, colecciones sincronizadas y concurrentes - Caso Dogs Race

### Descripción:
Este ejercicio tiene como fin que el estudiante conozca y aplique conceptos propios de la programación concurrente.

### Parte I 
Antes de terminar la clase.

Creación, puesta en marcha y coordinación de hilos.

1. Revise el programa “primos concurrentes” (en la carpeta parte1), dispuesto en el paquete edu.eci.arsw.primefinder. Este es un programa que calcula los números primos entre dos intervalos, distribuyendo la búsqueda de los mismos entre hilos independientes. Por ahora, tiene un único hilo de ejecución que busca los primos entre 0 y 30.000.000. Ejecútelo, abra el administrador de procesos del sistema operativo, y verifique cuantos núcleos son usados por el mismo.

En el procesos se usaron los 8 núcleos pero en la foto solo se alcanzan a ver los primeros cuatro núcles.

![Captura1](https://user-images.githubusercontent.com/37603257/106140607-fd44ab00-613c-11eb-9bef-cac4d82ec030.PNG)

2. Modifique el programa para que, en lugar de resolver el problema con un solo hilo, lo haga con tres, donde cada uno de éstos hará la tercera parte del problema original. Verifique nuevamente el funcionamiento, y nuevamente revise el uso de los núcleos del equipo.

![Captura3](https://user-images.githubusercontent.com/37603257/106141483-2580d980-613e-11eb-98a6-e0b93c2fa16c.PNG)

![Captura2](https://user-images.githubusercontent.com/37603257/106141389-ff5b3980-613d-11eb-9056-867542b56f89.PNG)


3. Lo que se le ha pedido es: debe modificar la aplicación de manera que cuando hayan transcurrido 5 segundos desde que se inició la ejecución, se detengan todos los hilos y se muestre el número de primos encontrados hasta el momento. Luego, se debe esperar a que el usuario presione ENTER para reanudar la ejecución de los mismo.

Se evidencia como se detienen los hilos a los 5 segundos de su ejecución.
![Captura4](https://user-images.githubusercontent.com/37603257/106153603-b8287500-614c-11eb-9059-d58c1dea0af8.PNG)

Posteriormente se evidencia que despues de los 5 segundos se le pide al usuario dar enter para seguir con el programa.
![Captura5](https://user-images.githubusercontent.com/37603257/106153599-b6f74800-614c-11eb-887c-ef20b0460f1f.PNG)



### Parte II 


Para este ejercicio se va a trabajar con un simulador de carreras de galgos (carpeta parte2), cuya representación gráfica corresponde a la siguiente figura:

![](./img/media/image1.png)

En la simulación, todos los galgos tienen la misma velocidad (a nivel de programación), por lo que el galgo ganador será aquel que (por cuestiones del azar) haya sido más beneficiado por el *scheduling* del
procesador (es decir, al que más ciclos de CPU se le haya otorgado durante la carrera). El modelo de la aplicación es el siguiente:

![](./img/media/image2.png)

Como se observa, los galgos son objetos ‘hilo’ (Thread), y el avance de los mismos es visualizado en la clase Canodromo, que es básicamente un formulario Swing. Todos los galgos (por defecto son 17 galgos corriendo en una pista de 100 metros) comparten el acceso a un objeto de tipo
RegistroLLegada. Cuando un galgo llega a la meta, accede al contador ubicado en dicho objeto (cuyo valor inicial es 1), y toma dicho valor como su posición de llegada, y luego lo incrementa en 1. El galgo que
logre tomar el ‘1’ será el ganador.

Al iniciar la aplicación, hay un primer error evidente: los resultados (total recorrido y número del galgo ganador) son mostrados antes de que finalice la carrera como tal. Sin embargo, es posible que una vez corregido esto, haya más inconsistencias causadas por la presencia de condiciones de carrera.

Parte III

1.  Corrija la aplicación para que el aviso de resultados se muestre
    sólo cuando la ejecución de todos los hilos ‘galgo’ haya finalizado.
    Para esto tenga en cuenta:

    a.  La acción de iniciar la carrera y mostrar los resultados se realiza a partir de la línea 38 de MainCanodromo.

    b.  Puede utilizarse el método join() de la clase Thread para sincronizar el hilo que inicia la carrera, con la finalización de los hilos de los galgos.
    
    Al utilizar el método join() de la clase Thread se logra sincronizar el hilo que inicia la carrera así mísmo corrigiendo las inconsistencias en los resultados de las carreras.
    
    ![image](https://user-images.githubusercontent.com/37603257/106761522-e69dc880-6602-11eb-8cb2-dcdedb1d0e46.png)
    ![image](https://user-images.githubusercontent.com/37603257/106761895-43997e80-6603-11eb-8028-1e011598f0af.png)
    ![image](https://user-images.githubusercontent.com/37603257/106761964-5ad86c00-6603-11eb-855a-7b265320834a.png)

2.  Una vez corregido el problema inicial, corra la aplicación varias
    veces, e identifique las inconsistencias en los resultados de las
    mismas viendo el ‘ranking’ mostrado en consola (algunas veces
    podrían salir resultados válidos, pero en otros se pueden presentar
    dichas inconsistencias). A partir de esto, identifique las regiones
    críticas () del programa.
    
    Al utilizar el método de synchronized() de la clase Thread se arreglan las inconsistencias que hay con respecto a los multiples resultados de los puestos en la carrera como se evidencia en la foto del punto anterior donde hay varios en el primer puesto.
    
    ![image](https://user-images.githubusercontent.com/37603257/106766014-6fb6fe80-6607-11eb-9499-0aa76b8f7a45.png)
    

3.  Utilice un mecanismo de sincronización para garantizar que a dichas
    regiones críticas sólo acceda un hilo a la vez. Verifique los
    resultados.
    
    ![image](https://user-images.githubusercontent.com/50029247/106797919-3f815700-662b-11eb-9804-ab7572a6edd9.png)
    
    Con el metodo implementado anteriormente obtenemos los siguientes resultados:
    
    ![image](https://user-images.githubusercontent.com/50029247/106801950-524a5a80-6630-11eb-921a-39976a8b169a.png)

4.  Implemente las funcionalidades de pausa y continuar. Con estas,
    cuando se haga clic en ‘Stop’, todos los hilos de los galgos
    deberían dormirse, y cuando se haga clic en ‘Continue’ los mismos
    deberían despertarse y continuar con la carrera. Diseñe una solución que permita hacer esto utilizando los mecanismos de sincronización con las primitivas de los Locks provistos por el lenguaje (wait y notifyAll).
    
    Para esto primero realizaremos un metodo get que le retorne el registro de llegada a la clase Galgo.java
    
    ![image](https://user-images.githubusercontent.com/50029247/106801425-bddff800-662f-11eb-8513-d09a09ee2303.png)
    
    En la clase Galgo.java realizaremos dos metodos, uno que asigne la variable isPause (Esta pausado) un valor booleando y el metodo pause para detener el programa.
    
    ![image](https://user-images.githubusercontent.com/50029247/106801670-08617480-6630-11eb-9a49-22de795e6ae0.png)
    
    Despues añadimos un condicional al metodo corra de la clase Galgo.java utilizando los metodos que implementamos anteriorment, ya podremos parar y reanudar la ejecución antes de que finalice.
    
    ![image](https://user-images.githubusercontent.com/50029247/106804704-bfabba80-6633-11eb-9398-9e68f9633f91.png)
    
    Asi se ve nuestro programa despues de iniciar y haberlo pausado:
    
    ![image](https://user-images.githubusercontent.com/50029247/106804995-1e713400-6634-11eb-8fdd-cf27741ab9e8.png)

    Esta seria la ejecución final después de interrumpir varias veces la ejecución:
    
    ![image](https://user-images.githubusercontent.com/50029247/106805271-80ca3480-6634-11eb-9d5d-e05c390d7cca.png)

    Y la salida por consola:
    
    ![image](https://user-images.githubusercontent.com/50029247/106805360-99d2e580-6634-11eb-93f4-96c49a5e2cdd.png)

    ![image](https://user-images.githubusercontent.com/50029247/106805880-3eedbe00-6635-11eb-8ec2-8c996b5934bf.png)


    
    
    
    

    

