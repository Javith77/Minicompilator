


import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static util.LexicalComponentUtil.G_PARAMETERS;
import static util.LexicalComponentUtil.G_STRUCTURE_FOR;
import static util.LexicalComponentUtil.G_STRUCTURE_FOR_WHIT_ASSIGN;
import static util.LexicalComponentUtil.G_VALUE;
import static util.LexicalComponentUtil.IDENTIFIER;
import static util.LexicalComponentUtil.NUMBER;
import static util.LexicalComponentUtil.RESERVED_ENUMERATE;
import static util.LexicalComponentUtil.RESERVED_FOR;
import static util.LexicalComponentUtil.RESERVED_IN;
import static util.LexicalComponentUtil.RESERVED_RANGE;
import static util.LexicalComponentUtil.STRING;
import static util.LexicalComponentUtil.SYMBOL_COMA;
import static util.LexicalComponentUtil.SYMBOL_EQUALS;
import static util.LexicalComponentUtil.SYMBOL_PARENTESIS_C;
import static util.LexicalComponentUtil.SYMBOL_PARENTESIS_O;
import static util.LexicalComponentUtil.SYMBOL_TWO_POINTS;
import static util.Message.MESSAGE_SYMBOL;
import static util.Message.MESSAGE_SYMBOL_PARENTESIS;
import static util.Message.MESSAGE_VALUE;

/**
 *
 * @author over.meneses
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Mini-compilator";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".test");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        
        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            
            int posicion = jtpCode.getCaretPosition();
            jtpCode.setText(jtpCode.getText().replaceAll("[\r]+", ""));
            jtpCode.setCaretPosition(posicion);
            
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{"for idx in items:","for idx in range(10):","var = for idx in range(10):","for count, value in enumerate(items):"}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                JOptionPane.showMessageDialog(null, "Ejecución exitosa" ,
                        "Código ejecutado con exito", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        try {
            File file = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(file);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            Lexer lexer = new Lexer(bufferedReader);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar grammar = new Grammar(tokens, errors);
        grammar.delete(new String[]{"ERROR"}, 1);//eliminar errores

        // agrupar valores
//        grammar.group(G_VALUE, createProduction(NUMBER,"|",STRING), true);

        //declaracion de variables
//        grammar.group(G_VARIABLE, createProduction(IDENTIFIER, SYMBOL_EQUALS, G_VALUE), true);
//        grammar.group(G_VARIABLE, createProduction(SYMBOL_EQUALS, G_VALUE), true, 1001, "Error sintáctico {}: falta nombrar una variable, línea #");
//        grammar.group(G_VARIABLE, createProduction(IDENTIFIER, G_VALUE), true, 1002, "Error sintáctico {}: falta el operador de asignación, línea #");

        grammar.initialLineColumn();

//        //eliminar operador de asignacion y valor
//        grammar.delete(NUMBER, 1003, "Error sintáctico {}: función declarada incorrectamente, línea #");
//        grammar.delete(STRING, 1004, "Error sintáctico {}: función declarada incorrectamente, línea #");
//        grammar.delete(SYMBOL_EQUALS, 1005, "Error sintáctico {}: función declarada incorrectamente, línea #");
        
        
        //estructura de agrupacion
        grammar.group(G_VALUE, createProduction(NUMBER,"|",STRING), true);
//        grammar.group(G_PARAMETERS, createProduction(G_VALUE, "(", SYMBOL_COMA, G_VALUE, ")+"));

        //estructura de control for var in var
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, IDENTIFIER, SYMBOL_TWO_POINTS), true);
        
        //estructura de control for var in range(var)
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, RESERVED_RANGE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS));
        
         //estructura de control $var = for var in range(var)
        grammar.group(G_STRUCTURE_FOR_WHIT_ASSIGN, createProduction(IDENTIFIER, SYMBOL_EQUALS, G_STRUCTURE_FOR), true);
        
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, IDENTIFIER), 1006, String.format(MESSAGE_SYMBOL, ":"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, SYMBOL_TWO_POINTS), 1007, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, IDENTIFIER, SYMBOL_TWO_POINTS), 1008, String.format(MESSAGE_SYMBOL, "in"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, RESERVED_IN, IDENTIFIER, SYMBOL_TWO_POINTS), 1009, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_IN, IDENTIFIER, SYMBOL_TWO_POINTS), 1010, String.format(MESSAGE_SYMBOL, "for"));

        grammar.group(G_STRUCTURE_FOR_WHIT_ASSIGN, createProduction(SYMBOL_EQUALS, G_STRUCTURE_FOR), 1011, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR_WHIT_ASSIGN, createProduction(IDENTIFIER, G_STRUCTURE_FOR), 1012, String.format(MESSAGE_SYMBOL, "="));
         
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, RESERVED_RANGE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C), 1013, String.format(MESSAGE_SYMBOL, ":"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, RESERVED_RANGE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_TWO_POINTS), 1014, MESSAGE_SYMBOL_PARENTESIS);
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, RESERVED_RANGE, SYMBOL_PARENTESIS_O, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1015, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, RESERVED_RANGE, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1016, MESSAGE_SYMBOL_PARENTESIS);
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_IN, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1017, String.format(MESSAGE_SYMBOL, "range"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, IDENTIFIER, RESERVED_RANGE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1018, String.format(MESSAGE_SYMBOL, "in"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, RESERVED_IN, RESERVED_RANGE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1019, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(IDENTIFIER, RESERVED_IN, RESERVED_RANGE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1020, String.format(MESSAGE_SYMBOL, "for"));
       
        //estructura de control for var, var in enumerate(var)
        grammar.group(G_VALUE, IDENTIFIER, true);
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), true);
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C), 1021, String.format(MESSAGE_SYMBOL, ":"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_TWO_POINTS), 1022, MESSAGE_SYMBOL_PARENTESIS);
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1023, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1024, MESSAGE_SYMBOL_PARENTESIS);
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1025, String.format(MESSAGE_SYMBOL, "enumerate"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1026, String.format(MESSAGE_SYMBOL, "in"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, SYMBOL_COMA, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1027, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, G_VALUE, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1028, String.format(MESSAGE_SYMBOL, ","));
        grammar.group(G_STRUCTURE_FOR, createProduction(RESERVED_FOR, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1029, String.format(MESSAGE_VALUE, "identificador"));
        grammar.group(G_STRUCTURE_FOR, createProduction(G_VALUE, SYMBOL_COMA, G_VALUE, RESERVED_IN, RESERVED_ENUMERATE, SYMBOL_PARENTESIS_O, G_VALUE, SYMBOL_PARENTESIS_C, SYMBOL_TWO_POINTS), 1030, String.format(MESSAGE_SYMBOL, "for"));
      
        grammar.initialLineColumn();
        //eliminar operador de agrupacion
        grammar.delete(new String[]{RESERVED_FOR, RESERVED_IN, SYMBOL_COMA,
            RESERVED_RANGE, RESERVED_ENUMERATE, SYMBOL_TWO_POINTS, G_VALUE}, 1031, "Error sintáctico {}: Caracter inválido [], línea #");

        //eliminar operador de agrupacion
        grammar.delete(new String[]{SYMBOL_PARENTESIS_O, SYMBOL_PARENTESIS_C}, 1032, MESSAGE_SYMBOL_PARENTESIS);
        
        //eliminar operador de asignacion y valor
        grammar.delete(NUMBER, 1034, "Error sintáctico {}: función declarada incorrectamente, línea #");
        grammar.delete(STRING, 1035, "Error sintáctico {}: función declarada incorrectamente, línea #");
        grammar.delete(SYMBOL_EQUALS, 1036, "Error sintáctico {}: función declarada incorrectamente, línea #");

        /* Mostrar gramáticas */
        grammar.show();
    }

    private void semanticAnalysis() {
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    private String createProduction(String... params){
        StringBuilder builder = new StringBuilder();
        for (String param : params) {
            builder.append(param.concat(" "));
        }
        
        System.out.printf("Production:: %s\n", builder.toString());
        return builder.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
