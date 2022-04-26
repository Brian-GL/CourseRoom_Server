clear all
close all
clc

%{
    Datos del alumno:
    Nombre: Gaytan Lomeli Brian Humberto
    Código: 214436871
    Carrera: Ingeniería en Computación.
%}

%{
    Datos de la materia:
    Nombre: Seminario de solución de problemas de inteligencia artificial I.
    Clave: I7039
    Sección: D04.
    Profesor: Hernández Barragán José De Jesús.
%}

%{
    Datos de la actividad:
    Proyecto Final: Correlación Cruzada Normalizada.
    Descripción: Resuelve cualquiera de las propuestas de proyecto como un problema de optimización 
    con restricciones. Puedes utilizar el algoritmo evolutivo de tu preferencia.
%}


%Para comenzar con la resolución del proyecto, debemos primero cargar y crear todos los datos necesarios de la problemática, es decir, las imágenes, H y W, etc.

%Cargamos la imagen original (la imagen en donde buscaremos la plantilla).
%La llamaremos Imagen tal cual:

Imagen = imread('Image_1.bmp');

%Ahora, cargamos la segunda imagen (la plantilla). Le daremos de nombre Plantilla tal cual:

Plantilla = imread('Template.bmp');

%Ahora, requerimos hacer que las imagenes se encuentren en escala de grises, no en formato RGB. Así que, las convertiremos:

%Mediante la función rgb2gray, pasaremos a la Imagen Original (que guardamos con nombre Imagen) 
%como parámetro para que nos regrese tal imagen en blanco y negro.

%Al valor obtenido le llamaremos ImagenTratada:
ImagenTratada = rgb2gray(Imagen);

%Hacemos lo mismo para la plantilla. Al resultado lo llamaremos PlantillaTratada:

PlantillaTratada = rgb2gray(Plantilla);

%Ahora obtenemos los tamaños, en cada dimesión, de ambas imagenes. Tales valores los almacenamos en matrices:

[H,W] = size(ImagenTratada);
[h,w] = size(PlantillaTratada);

%A continuación, establecemos los valores de los parámetros que requiere el algoritmo:

%---------------------------------------------------------
%Dimensión:

Dimension = 2;%Definimos el valor de la dimensión. En este caso es dos, ya que solamente requerimos obtenemos los valores de x e y del punto en la imagen que representa el match de la plantilla con esta.

%---------------------------------------------------------

%---------------------------------------------------------

%---------------------------------------------------------

disp('Correlación Cruzada Normalizada');

%---------------------------------------------------------
%Número De Individuos:

%Pedimos primero el número de individuos que tendrá nuestra población:
NumeroDeIndividuos = -1;
while NumeroDeIndividuos < 5 %Definimos también que el número de individuos, en este caso, debe de ser mayor a 5 para ser un valor válido (ya que en la polinización local se requieren por lo menos 3 individuos diferentes (Xi, Xj & Xk)).
    NumeroDeIndividuos = input('\nIngrese El Número De Individuos Que Tendrá La Población (Mínimo 5):\n>>> ');
end

%---------------------------------------------------------

%---------------------------------------------------------
%Número De Iteraciones:

%Pedimos el número de iteraciones a generar:
NumeroDeIteraciones= -1;
while NumeroDeIteraciones <= 0
    NumeroDeIteraciones = input('\nIngrese El Número De Iteraciones A Generar:\n>>> ');
end

%---------------------------------------------------------

%---------------------------------------------------------
%Límites:

%En este caso los límites no serán pedidos, se obtendrán automáticamente, con
%el propósito de evitar crear individuos en luagres  donde el tamaño de la plantilla supere el tamaño de la imagen original. 

%El valor mínimo en cada dimensión [x,y] es 1, ya que no hay posiciones negativas (x,y) en la imagen.
Minimo = [1 1]'; %Por lo tanto, la matriz de mínimo será de valores 1 y 1 para x e y.

%El valor máximo en x será el ancho de la imagen original menos el ancho de la plantilla. Así aseguramos lo descrito anteriormente:
LimiteMaximoX = W - w;

%Y para el límite máximo en y será el alto de la imagen original menos el alto de la plantilla:
LimiteMaximoY = H - h;

%Entonces, creamos la matrix de Máximo utilizando los valores obtenidos con anterioridad.
Maximo = [LimiteMaximoX LimiteMaximoY]'; 

%---------------------------------------------------------

%---------------------------------------------------------
%Parámetros constantes requeridos por los algoritmos:

%Definimos los valores constantes con valores aceptables:

ParametroDePaso = 1.5;
CriterioDeProbabilidad = 0.6; %en este caso el criterio de probabilidad esta entre 0.5, ya que en este algoritmo la polinización local (algoritmo de evolución diferencial) genera resultados muy favorables, por lo que es de suma importancia que este método se realize constantemente.
FactorDeAmplificacion = 1.2;
ConstanteDeRecombinacion = 0.9;

%---------------------------------------------------------

%Obtenemos y mostramos los resultados obtenidos:

format longEng

%---------------------------------------------------------
%Polinización evolutiva (Algoritmo híbrido creado):
disp(' ');

Solucion = PolinizacionEvolutiva(ImagenTratada,PlantillaTratada,NumeroDeIteraciones,NumeroDeIndividuos, Minimo, Maximo, Dimension, ParametroDePaso, CriterioDeProbabilidad ,FactorDeAmplificacion,ConstanteDeRecombinacion);

disp('Resultado De (x,y) Obtenido: ');
valor = sprintf('X: %d  | Y: %d | Evaluación: %d\n',Solucion(1),Solucion(2),NCC(ImagenTratada,PlantillaTratada,Solucion));
disp(valor);

%Mostramos la imagen con el match encontrado:
figure('Name','  R e s u l t a d o  O b t e n i d o')
hold on

imshow(Imagen);

%En variables auxiliares guardamos los valores de x e y respectivos al valor de la Solución encontrada:
xp = Solucion(1);
yp = Solucion(2);

%Dibujamos el match en la imagen:
line([xp xp+w], [yp yp],'Color','r','LineWidth',3);
line([xp xp], [yp yp+h],'Color','r','LineWidth',3);
line([xp+w xp+w], [yp yp+h],'Color','r','LineWidth',3);
line([xp xp+w], [yp+h yp+h],'Color','r','LineWidth',3);


