package Presentation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Storage.DbFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ScreenAdm {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DbFunctions.getConnection();
            do {
                do {
                    System.out.print("Digite 1(adiconar), 2(filtrar) ou 3(sair): ");
                    escolha = INPUT.nextInt();

                    if (escolha > 3 || escolha < 1) {
                        System.out.println("Escolha inválida!");
                    }
                } while (escolha > 3 || escolha < 1);
                INPUT.nextLine();

                if (escolha == 1) {

                    System.out.print("Digite o nome do projeto: ");
                    String name = INPUT.nextLine();

                    System.out.print("Digite o nome da Organização: ");
                    String organization = INPUT.nextLine();

                    boolean validadeDaData = false;
                    int ano = 0;
                    int mes = 0;
                    int dia = 0;
                    do {
                        LocalDate dataAtual = LocalDate.now();
                        int[] meses30 = new int[] { 1, 3, 4, 5, 6, 7, 8, 9, 11, 12 };
                        int[] meses31 = new int[] { 1, 3, 5, 7, 8, 10, 12 };

                        System.out.print("Digite a data do projeto (ANO): ");
                        ano = INPUT.nextInt();
                        if (ano > dataAtual.getYear() || ano < 2000) {
                            System.out.println("Data inválida!");
                            continue;
                        }

                        boolean anoBissexto = (ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0);

                        System.out.print("Digite a data do projeto (MÊS): ");
                        mes = INPUT.nextInt();

                        if (mes < 1 || mes > 12 || (mes > dataAtual.getMonthValue() && ano == dataAtual.getYear())) {
                            System.out.println("Data inválida!");
                            continue;
                        }

                        System.out.print("Digite a data do projeto (DIA): ");
                        dia = INPUT.nextInt();

                        if (dia < 1 || dia > 31) {
                            System.out.println("Data inválida!");
                            continue;
                        } else if ((dia == 31 && Arrays.binarySearch(meses31, mes) == -1)
                                || (dia == 30 && Arrays.binarySearch(meses30, mes) == -1)) {
                            System.out.println("Data inválida!");
                            continue;
                        } else if (mes == 2 && dia == 29 && !anoBissexto) {
                            System.out.println("Data inválida!");
                            continue;
                        }
                        validadeDaData = true;
                        System.out.println("Data válida");

                    } while (!validadeDaData);

                    String[] data = new String[3];
                    String mesStr = Integer.toString(mes);
                    String diaStr = Integer.toString(dia);

                    if (mes < 10) {
                        mesStr = "0" + mes;
                    }
                    if (dia < 10) {
                        diaStr = "0" + dia;
                    }
                    data[0] = Integer.toString(ano);
                    data[1] = mesStr;
                    data[2] = diaStr;

                    String dataStr = String.join("-", data);
                    // System.out.println(String.join("-", data));

                    double custoInicial = 0;
                    do {
                        System.out.print("Custo inicial: R$");
                        custoInicial = INPUT.nextDouble();
                        if (custoInicial < 0) {
                            System.out.println("O custo inicial não pode ser negativo.");
                        }
                    } while (custoInicial < 0);

                    System.out.print("Coloque o link do projeto aqui: ");
                    String link = INPUT.next();

                    DbFunctions.addProject(con, st, name, organization, dataStr, custoInicial, link);
                    System.out.println("Projeto adicionado!");

                } else if (escolha == 2) {
                    System.out.print("Digite o valor de 'Name' do projeto para filtrar: ");
                    String searchString = INPUT.nextLine();
                    DbFunctions.searchByName(con, st, rs, searchString);
                }
            } while (escolha != 3);

            System.out.println("Saindo...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbFunctions.closeConnection();
        }
    }
}
