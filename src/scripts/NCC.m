%Funcion que nos ayuda a obtener la evaluaci�n del individuo en la f�rmula
%de Correlaci�n Cruzada Normalizada.

%En donde requerimos los siguientes par�metros:

%{
 
 Imagen: Representa la imagen original.
 Plantilla: Representa la imagen plantilla a buscar en la imagen original.
 Individuo: Representa el valor del individuo del algoritmo a evaluar. 

%}

%Retornamos el valor escalar obtenido por la funci�n NCC [-1;1].

function Evaluacion = NCC(Imagen,Plantilla,Individuo)
    
    [H,W] = size(Plantilla); %Tama�o de la plantilla (Alto x Ancho)
	
    %Posiciones x e y del Individuo.

    x = Individuo(1);
    y = Individuo(2);
    
    %Valores auxiliares para las sumatorias que la funci�n requiere.
    SumatoriaDeLaImagen = 0.0;
    SumatoriaDeLaPlantilla = 0.0;
    sum_2 = 0.0;



    for i=1:W-1
        for j=1:H-1
            SumatoriaDeLaImagen = SumatoriaDeLaImagen + double(Imagen(y+j,x+i))^2;
            SumatoriaDeLaPlantilla = SumatoriaDeLaPlantilla + double(Plantilla(j,i))^2;
            sum_2 = sum_2 + double(Imagen(y+j,x+i))*double(Plantilla(j,i));
        end
    end
    %Obtenemos el valor de la evaluaci�n apoy�ndomos en las sumatorias obtenidas con anterioridad.
    Evaluacion = sum_2/(sqrt(double(SumatoriaDeLaImagen))*sqrt(double(SumatoriaDeLaPlantilla)));
end