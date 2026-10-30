/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miproyect;

import java.util.Scanner;

public class SistemaVentas {
    
    public static void Menu(){
        Scanner teclado = new Scanner (System.in);
        
        //====================Datos==============
        int totalVentas = 0;
        int unidadesVendidas = 0;
        double dineroTotal = 0;
        int cantidadEfectivo = 0;
        int cantidadTarjeta = 0;
        int cantidadTransferencia = 0;
        int ventasSobre500 = 0;
        int ventasconDescuento = 0;
        double mayorVentas = 0;
        double menorVentas = 0;
        boolean esprimeraVenta = true;
        
        boolean terminar = false;
        
        //===============MENU===========
        do {
            System.out.println("===================");
            System.out.println("       TIENDA      ");
            System.out.println("===================");
            System.out.println("1. registrar una venta");
            System.out.println("2. Consultar caja");
            System.out.println("3. Mostrar estadisticas");
            System.out.println("4. Salir");
            
            int menu = 0;
            boolean leido = false;
            while (!leido) {
                System.out.println("Opcion");
                if (teclado.hasNextInt()) {
                    menu = teclado.nextInt();
                    leido = true;
                }else{
                    teclado.next();
                    System.out.println("No valido. Escribe un numero del 1 al 4");
                }
            }
            teclado.nextLine();
            
            switch (menu) {
                case 1:
                    System.out.println("=== Nueva Venta===");
                    
                    // Numero de Productos//
                    int lineas = 0;
                    leido = false;
                    while (!leido){
                        System.out.println("numero de productos de la compra: ");
                        if (teclado.hasNextInt()) {
                            lineas = teclado.nextInt();
                            if (lineas > 0){
                                leido = true;
                            }else{
                                System.out.println("Debe ser mayor a 0");
                            }
                        }else{
                            teclado.next();
                            System.out.println("Error. Escriba un numero entero");
                        }
                    }
                    teclado.nextLine();
                    
                    double subtotal = 0;
                    int unidadesventa = 0;
                    
                    //Productos//
                    for (int n = 1; n <= lineas; n++){
                        System.out.println("=== Producto #" + n + " de" + lineas + "===");
                        
                        System.out.println("Codigo");
                        String cod = teclado.nextLine();
                        System.out.println("Nombre");
                        String nom = teclado.nextLine();
                        
                        // Precio //
                        double pu = 0;
                        leido = false;
                        while (!leido) {
                            System.out.println("Precio Unitario: Q");
                            if (teclado.hasNextDouble()) {
                                pu = teclado.nextDouble();
                                if (pu > 0) {
                                    leido = true;
                                }else{
                                    System.out.println("El precio debe ser mayor a cero");
                                }
                            }else{
                                teclado.next();
                                System.out.println("Error. Ocupe punto para decimal");
                                
                                }
                        }
                        //Cantidad
                        int cantidad = 0;
                        leido = false;
                        while (!leido) {
                            System.out.println("Cantidad: ");
                            if (teclado.hasNextInt()) {
                                cantidad = teclado.nextInt();
                                if (cantidad > 0){
                                    leido = true;
                                }else{
                                    System.out.println("La Cantidad debe ser mayor que cero");
                                }
                            }else{
                                teclado.next();
                                System.out.println("Error. Escriba un numero entero.");
                            }
                        }
                        teclado.nextLine();
                        
                        double parcial = pu * cantidad;
                        subtotal = subtotal + parcial;
                        unidadesVendidas = unidadesventa + cantidad;
                        System.out.printf("   %s [%s] -> Q%.2f%n", nom, cod, parcial);
                        
                    }
                    //Descuento//
                    double porcentaje;
                    if (subtotal < 200) {
                        porcentaje = 0;
                    }else if (subtotal < 500){
                        porcentaje = 5;
                    }else if (subtotal < 1000){
                        porcentaje = 8;
                    }else{ 
                        porcentaje = 10;
                    }
                    double rebaja = subtotal * (porcentaje /100);
                    double baseImponible = subtotal - rebaja;
                    double impuesto = baseImponible * 0.12;
                    double totalPagar = Math.round((baseImponible + impuesto) * 100) / 100.0;
                    
                    System.out.printf("Subtotal ........ Q%.2f%n", subtotal);
                    System.out.printf("Descuento (%.0f%%) .. Q%.2f%n", porcentaje, rebaja);
                    System.out.printf("IVA (12%%) ....... Q%.2f%n", impuesto);
                    System.out.printf("TOTAL ........... Q%.2f%n", totalPagar);
                    
                    //FORMA DE PAGO//
                    boolean pagoListo = false;
                    String metodo = "";
                    double recibido = 0;
                    double vuelto = 0;
                    
                    while (!pagoListo) {
                    System.out.println("Seleccione la forma de pago");
                        System.out.println("1) Efectivo");
                        System.out.println("2) Tarjeta");
                        System.out.println("3) Transferencia");
                        
                        int forma = 0;
                        leido = false;
                        while (!leido) {
                            System.out.print("Opcion: ");
                            if (teclado.hasNextInt()) {
                                forma = teclado.nextInt();
                                leido = true;
                            }else{
                                teclado.next();
                                System.out.println("No valido. Escriba 1, 2 o 3.");
                            }
                        }
                        teclado.nextLine();
                        
                        switch (forma) {
                            case 1:
                                //EFECTIVO//
                                recibido = 0;
                                leido = false;
                                while (!leido) {
                                    System.out.print("Monto Recibido: Q");
                                    if (teclado.hasNextDouble()) {
                                        recibido = teclado.nextDouble();
                                        if (recibido >= totalPagar) {
                                            leido = true;
                                        }else{
                                            System.out.printf("Efectivo insuficiente, faltan Q%.2f%n", totalPagar - recibido);
                                            
                                        }
                                    }else{
                                        teclado.next();
                                        System.out.println("Error. Use punto para decimal");
                                    }
                                }
                                teclado.nextLine();
                                vuelto = recibido - totalPagar;
                                metodo = "Efectivo";
                                pagoListo = true;
                                break;
                                
                            case 2:
                                System.out.println("Esperando respuesta de la tarjera...");
                                int aprobado = 0;
                                leido = false;
                                while (!leido) {
                                    System.out.print("¿Fue aprobada?1) Si 2) No");
                                    if (teclado.hasNextInt()) {
                                        aprobado = teclado.nextInt();
                                        if (aprobado == 1 || aprobado == 2) {
                                            leido = true;
                                        }else{
                                            System.out.println("Escriba 1 o 2");
                                        }
                                    }else{
                                        teclado.next();
                                        System.out.println("Error. Escriba 1 o 2");
                                    }
                                }
                                teclado.nextLine();
                                if (aprobado == 1){
                                    metodo = "Tarjeta";
                                    pagoListo = true;
                                }else{
                                    System.out.println("transferencia no recibida. Elija otra vez la forma de pago");
                                }
                                break;
                            case 3:
                                int recibida = 0;
                                leido =false;
                                while (!leido) {
                                    System.out.print("¿Se recibio la transferencia? 1) Si 2) No: ");
                                    
                                    if (teclado.hasNextInt()) {
                                        recibida = teclado.nextInt();
                                        if (recibida == 1 || recibida == 2){
                                            leido = true;
                                        }
                                    }else{
                                        System.out.println("Escriba 1 o 2");
                                    }
                                }
                                teclado.nextLine();
                                if(recibida == 1){
                                    metodo = "Transferencia";
                                    pagoListo = true;
                                }else{
                                    System.out.println("Transferencia no recibida. Elija otra vez la forma de pago");
                                        }
                                break;
                                
                            default:
                                System.out.println("Forma de pago no valida. Elija 1, 2 o 3");
                        }
                    }
                    
                    //Actualiar Caja y Estadisticas//
                    totalVentas++;
                    unidadesVendidas = unidadesVendidas + unidadesventa;
                    dineroTotal = dineroTotal + totalPagar;
                    
                    if (metodo.equals("Efectivo")) {
                        cantidadEfectivo++;
                    } else if (metodo.equals("Tarjeta")) {
                        cantidadTarjeta++;
                    }else{
                        cantidadTransferencia++;
                    }
                    
                    if (totalPagar > 500) {
                        ventasSobre500++;
                    }
                    if (rebaja > 0) {
                        ventasconDescuento++;
                    }
                    
                    if (esprimeraVenta) {
                        mayorVentas = totalPagar;
                        menorVentas = totalPagar;
                        esprimeraVenta = false;
                    }else{
                        if (totalPagar > mayorVentas) {
                        mayorVentas = totalPagar;
                        }
                        if (totalPagar < menorVentas) {
                            menorVentas = totalPagar;
                        }
                    }
                    
                    //FINAL DE VENTAS//
                    System.out.println("========= RESUMEN DE VENTA =========");
                    System.out.println("Productos (Unidades): " + unidadesventa);
                    System.out.printf("Subtotal: Q%.2f%n", subtotal);
                    System.out.printf("Descuento: Q%.2f (%.0f%%)%n", rebaja, porcentaje);
                    System.out.printf("IVA: Q%.2f%n", impuesto);
                    System.out.printf("Total: Q%.2f%n", totalPagar);
                    System.out.println("Forma de pago:" + metodo);
                    if (metodo.equals("Efectivo")){
                        System.out.printf("Efectivo recibido: Q%.2f%n", recibido);
                        System.out.printf("Cambio: Q%.2f%n", vuelto);
                    }
                    System.out.println("=====================================");
                    break;
                    
                case 2:
                    System.out.println("==== CAJA ====");
                    System.out.println("Ventas realizadas" + totalVentas);
                    System.out.println("Productos vendidos:" + unidadesVendidas);
                    System.out.printf("Total vendido: Q%.2f%n", dineroTotal);
                    System.out.println("Ventas en efectivo: " + cantidadEfectivo);
                    System.out.println("ventas con tarjeta " + cantidadTarjeta);
                    System.out.println("ventas por transferencia: " + cantidadTransferencia);
                    break;
                    
                case 3:
                    System.out.println("===== ESTADISTICAS =====");
                    if (totalVentas == 0) {
                        System.out.println("No existe ventas registradas");
                    }else{
                        double promedio = dineroTotal / totalVentas;
                        System.out.println("Total de Ventas: " + totalVentas);
                        System.out.println("Productos vendidos: " + unidadesVendidas);
                        System.out.printf("Dinero Vendido: Q%.2f%n", dineroTotal);
                        System.out.printf("Promedio por venta: Q%.2f%n", promedio);
                        System.out.println("Ventas en efectivo: " + cantidadEfectivo);
                        System.out.println("Ventas en tarjeta " + cantidadTarjeta);
                        System.out.println("Ventas en Transferencia " + cantidadTransferencia);
                        System.out.printf("Ventas de mayor valor: Q%.2f%n", mayorVentas);
                        System.out.printf("Ventas de menor valor: Q%.2f%n ", menorVentas);
                        System.out.println("Ventas que superaron Q500: " + ventasSobre500);
                        System.out.println("ventas con descuento: " + ventasconDescuento);
                    }
                    break;
                    
                case 4:
                    int confirmar = 0;
                    leido = false;
                    while (!leido){
                        System.out.print("¿Deseas salir? 1) Si 2) No: ");
                        if (teclado.hasNextInt()){
                            confirmar = teclado.nextInt();
                            if(confirmar == 1 || confirmar == 2){
                                leido = true;
                            }else{
                                System.out.println("Escriba 1 o 2");
                            }
                        }else{
                            teclado.next();
                            System.out.println("Error. Escriba 1 o 2");
                        }
                    }
                    teclado.nextLine();
                    if (confirmar == 1) {
                        terminar = true;
                        System.out.println("Programa finalizado");
                    }
                    break;
                    
                default:
                    System.out.println("Opcion no valida, elija del 1 al 4");
            }
        }while (!terminar);
            
            
            teclado.close();
        }
                           
                                
                        
                        
               
                    
                        
                        
                        
                        
                        
                        
                            
                        
                       
                    
                                            
                                
                                        }
                    
                    
                    
                        
                        
                        
                        
                        
                        
                        
                        
                                
                        
                        
                        
                                    
                                
                      
                    
                            
                        
                        
                    
                    
                
            
        
                
        
   
    


/**
 *
 */
     
