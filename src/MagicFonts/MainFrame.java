package MagicFonts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.InputVerifier;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;


/**
 *
 * @author ipopov
 */
public class MainFrame extends javax.swing.JFrame {

    private GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    JFileChooser dialogChooser = null;

    private String choosedFont = null;
    private static String currentFont = null;

    private static boolean isNewFont = true;
    private ShowFontTable showFontPanelTable = null;
    protected static ShowCustomFont showCustomFont = null;
    protected static ShowImageFont showImageFont = null;
    protected static Color newColorFont = null;
    protected static Color newColorOutline = null;
    protected static ResourceBundle langResources = null;
    private ProjectTools pt = null;

    private SpinnerNumberModel outlineWidthSpinnerModel = null;

    private ProjectFilter projectFilter = null;
    private TTFFilter ttff = null;
    private PictureFilter picf = null;

    public static ProgramSettings ps = null;
    private ClipboardOperations clipboardOp = null;

    /** Creates new form MainFrame */
    public MainFrame() {
        clipboardOp = new ClipboardOperations();
        
        ps = new ProgramSettings();
        dialogChooser = new JFileChooser( ps.getField("LoadFontDefaultPath") );
        projectFilter = new ProjectFilter();
        ttff = new TTFFilter();
        picf = new PictureFilter();
        
        dialogChooser.addChoosableFileFilter(projectFilter);
        dialogChooser.addChoosableFileFilter(ttff);
        dialogChooser.addChoosableFileFilter(picf);
        dialogChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        pt = new ProjectTools();
        ProjectTools.status = ProjectTools.projectStatus[0];
        ProjectTools.statuses.put(ProjectTools.projectStatus[0], ProjectTools.projectStatus[0]);
        //размер на главният прозорец
        this.setPreferredSize(new Dimension(1000,800));
        showCustomFont = new ShowCustomFont();
        showImageFont = new ShowImageFont();
        showImageFont.setPreferredSize(null);
        showFontPanelTable = new ShowFontTable();

        Locale locale = Locale.getDefault();
        langResources = ResourceBundle.getBundle("MagicFonts.interface_en", locale);

        Float current = new Float(0.00);
        Float min = new Float(0.00);
        Float max = new Float(20.00);
        Float step = new Float(0.2);
        outlineWidthSpinnerModel = new SpinnerNumberModel(current, min, max, step);

        initComponents();
        //добавяне на класът за рисуване към панел
        jPanel4.add(showImageFont);
        //къде ще се разполага в панела - панелът може да се използва и като контейнер за други елементи
        showImageFont.setLocation(0,0);
        showImageFont.setSize( jPanel4.getWidth(), jPanel4.getHeight());
        showImageFont.setVisible(true);

        currentFont = fonts.getModel().getSelectedItem().toString();
        
        clipboardOp.setComponentAction(textFont, Constants.CLIPBOARD_ACTIONS_COPY);
        copyButton.addActionListener(clipboardOp);

        //инициализиране на цветовете
        newColorFont = colorLabel.getBackground();
        newColorOutline = outlineColorLabel.getBackground();
        //ориентиране на текста около checkboxes
        italicCheckBox.setHorizontalTextPosition(AbstractButton.LEADING);
        italicCheckBox.setVerticalTextPosition(AbstractButton.CENTER);

        boldCheckBox.setHorizontalTextPosition(AbstractButton.LEADING);
        boldCheckBox.setVerticalTextPosition(AbstractButton.CENTER);

        bitmapFWTCheckBox.setHorizontalTextPosition(AbstractButton.LEADING);
        bitmapFWTCheckBox.setVerticalTextPosition(AbstractButton.CENTER);

        this.mainSplitPane.setDividerLocation(this.getWidth()/2);
    }
    
    /**
     * Реализира реакция при излизане от програмата
     * от системното меню
     * @param e
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if ( e.getID() == WindowEvent.WINDOW_CLOSING ) {
            this.exitFromProgram();
        }
    }

    private void exitFromProgram() {
        SimpleMessage sm = new SimpleMessage("quest", 2);
        sm.setStrButton1(langResources.getString("message_button_exit"));
        sm.setStrButton2(langResources.getString("message_button_cancel"));
        sm.setBaseMessage(langResources.getString("message_question_exit"));
        if (sm.Message()) {
            System.exit(0);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        showFontScrollPane = new javax.swing.JScrollPane(showFontPanelTable);
        rightSplitPane = new javax.swing.JSplitPane();
        customFontScrollPane = new javax.swing.JScrollPane(showCustomFont);
        toImageScrollPane = new javax.swing.JScrollPane();
        toImageScrollPane.setViewportView(jPanel4);
        ;
        jPanel4 = new javax.swing.JPanel();
        operationsTabbedPane = new javax.swing.JTabbedPane();
        loadFontPanel = new javax.swing.JPanel();
        clearPanel = new javax.swing.JPanel();
        clearAllButton = new javax.swing.JButton();
        clearSelRowsButton = new javax.swing.JButton();
        clearCellButton = new javax.swing.JButton();
        loadFontsPanel = new javax.swing.JPanel();
        choiceFontLabel = new javax.swing.JLabel();
        fonts = new JComboBox(gEnvironment.getAvailableFontFamilyNames());
        customFontCheckBox = new javax.swing.JCheckBox();
        loadFontButton = new javax.swing.JButton();
        fontsSettingsPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fontSizeLabel = new javax.swing.JLabel();
        fontSizeSpinner = new javax.swing.JSpinner();
        italicCheckBox = new javax.swing.JCheckBox();
        boldCheckBox = new javax.swing.JCheckBox();
        colorLabel = new javax.swing.JLabel();
        cColorLabel = new javax.swing.JLabel();
        alphabetSettingsPanel = new javax.swing.JPanel();
        alphabetSizeLabel = new javax.swing.JLabel();
        alphabetSizeTextField = new javax.swing.JTextField();
        sideLabel = new javax.swing.JLabel();
        sideCharSpinner = new javax.swing.JSpinner();
        bitmapFWTCheckBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        strikethroughCheckBox = new javax.swing.JCheckBox();
        effectsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        outlineCheckBox = new javax.swing.JCheckBox();
        outlineColorLabel = new javax.swing.JLabel();
        cOutlineColorLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lineWidthSpinner = new javax.swing.JSpinner();
        applyButton = new javax.swing.JButton();
        customFontLabel = new javax.swing.JLabel();
        savePictureButton = new javax.swing.JButton();
        textFont = new javax.swing.JTextField();
        copyButton = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(MainFrame.ps.getField("Name"));

        mainSplitPane.setDividerLocation(250);
        mainSplitPane.setDoubleBuffered(true);
        mainSplitPane.setLeftComponent(showFontScrollPane);

        rightSplitPane.setDividerLocation(280);
        rightSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        customFontScrollPane.setMaximumSize(new java.awt.Dimension(327, 327));
        rightSplitPane.setTopComponent(customFontScrollPane);

        toImageScrollPane.setAutoscrolls(true);
        toImageScrollPane.setDoubleBuffered(true);
        toImageScrollPane.setMaximumSize(new java.awt.Dimension(327, 327));
        toImageScrollPane.setRequestFocusEnabled(false);

        jPanel4.setAutoscrolls(true);
        jPanel4.setPreferredSize(new java.awt.Dimension(575, 247));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        toImageScrollPane.setViewportView(jPanel4);

        rightSplitPane.setBottomComponent(toImageScrollPane);

        mainSplitPane.setRightComponent(rightSplitPane);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("MagicFonts/interface_en"); // NOI18N
        clearPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("tab_bmp_edit_fonts_clearpanel_caption"))); // NOI18N

        clearAllButton.setText(bundle.getString("tab_bmp_edit_fonts_button_clearall_caption")); // NOI18N
        clearAllButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearAllButtonMouseClicked(evt);
            }
        });

        clearSelRowsButton.setText(bundle.getString("tab_bmp_edit_fonts_button_clearselrows_caption")); // NOI18N
        clearSelRowsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearSelRowsButtonMouseClicked(evt);
            }
        });

        clearCellButton.setText(bundle.getString("tab_bmp_edit_fonts_button_cell_caption")); // NOI18N
        clearCellButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearCellButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout clearPanelLayout = new javax.swing.GroupLayout(clearPanel);
        clearPanel.setLayout(clearPanelLayout);
        clearPanelLayout.setHorizontalGroup(
            clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clearPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(clearAllButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearSelRowsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearCellButton)
                .addGap(10, 10, 10))
        );
        clearPanelLayout.setVerticalGroup(
            clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clearPanelLayout.createSequentialGroup()
                .addGroup(clearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearCellButton)
                    .addComponent(clearSelRowsButton)
                    .addComponent(clearAllButton))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        loadFontsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("tab_bmp_edit_fonts_loadfonts_caption"))); // NOI18N

        choiceFontLabel.setText(bundle.getString("tab_fonts_label")); // NOI18N
        choiceFontLabel.setAlignmentY(0.3F);

        fonts.setAlignmentY(0.3F);
        fonts.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fontsItemStateChanged(evt);
            }
        });

        customFontCheckBox.setText(bundle.getString("tab_fonts_load_custom_checkbox")); // NOI18N
        customFontCheckBox.setAlignmentY(0.3F);
        customFontCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customFontCheckBoxItemStateChanged(evt);
            }
        });

        loadFontButton.setText(bundle.getString("tab_fonts_load_custom_button")); // NOI18N
        loadFontButton.setAlignmentY(0.3F);
        loadFontButton.setFocusPainted(false);
        loadFontButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFontButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loadFontsPanelLayout = new javax.swing.GroupLayout(loadFontsPanel);
        loadFontsPanel.setLayout(loadFontsPanelLayout);
        loadFontsPanelLayout.setHorizontalGroup(
            loadFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadFontsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loadFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loadFontButton)
                    .addComponent(customFontCheckBox)
                    .addGroup(loadFontsPanelLayout.createSequentialGroup()
                        .addComponent(choiceFontLabel)
                        .addGap(18, 18, 18)
                        .addComponent(fonts, 0, 148, Short.MAX_VALUE)))
                .addContainerGap())
        );
        loadFontsPanelLayout.setVerticalGroup(
            loadFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadFontsPanelLayout.createSequentialGroup()
                .addGroup(loadFontsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choiceFontLabel)
                    .addComponent(fonts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(customFontCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadFontButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout loadFontPanelLayout = new javax.swing.GroupLayout(loadFontPanel);
        loadFontPanel.setLayout(loadFontPanelLayout);
        loadFontPanelLayout.setHorizontalGroup(
            loadFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadFontPanelLayout.createSequentialGroup()
                .addComponent(loadFontsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(374, 374, 374))
        );
        loadFontPanelLayout.setVerticalGroup(
            loadFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadFontPanelLayout.createSequentialGroup()
                .addGroup(loadFontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clearPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadFontsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        operationsTabbedPane.addTab(bundle.getString("tab_fonts_caption"), loadFontPanel); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("tab_bmp_fonts_common_settings"))); // NOI18N

        fontSizeLabel.setLabelFor(fontSizeSpinner);
        fontSizeLabel.setText(bundle.getString("tab_font_sett_fontsize_label")); // NOI18N

        fontSizeSpinner.setModel(new javax.swing.SpinnerNumberModel(22, 6, 120, 2));
        fontSizeSpinner.setRequestFocusEnabled(false);

        italicCheckBox.setText(bundle.getString("tab_bmp_fonts_common_settings_italic")); // NOI18N
        italicCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        italicCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                italicCheckBoxItemStateChanged(evt);
            }
        });

        boldCheckBox.setText(bundle.getString("tab_bmp_fonts_common_settings_bold")); // NOI18N
        boldCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boldCheckBoxItemStateChanged(evt);
            }
        });

        colorLabel.setBackground(new java.awt.Color(0, 0, 0));
        colorLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        colorLabel.setOpaque(true);
        colorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                colorLabelMousePressed(evt);
            }
        });

        cColorLabel.setText(bundle.getString("tab_bmp_fonts_common_settings_color")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fontSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addComponent(italicCheckBox))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boldCheckBox)
                    .addComponent(fontSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cColorLabel)
                .addGap(69, 69, 69)
                .addComponent(colorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fontSizeLabel)
                    .addComponent(fontSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(italicCheckBox)
                    .addComponent(boldCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cColorLabel)
                    .addComponent(colorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        alphabetSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("tab_bmp_fonts_alphabet_settings"))); // NOI18N

        alphabetSizeLabel.setText(bundle.getString("tab_bmp_fonts_alphabet_size")); // NOI18N

        alphabetSizeTextField.setText("22");
        alphabetSizeTextField.setInputVerifier(new AlphabetSizeVerifier());

        sideLabel.setText(bundle.getString("tab_bmp_fonts_alphabet_rectangle")); // NOI18N

        sideCharSpinner.setModel(new javax.swing.SpinnerNumberModel(28, 10, 100, 1));

        bitmapFWTCheckBox.setText(bundle.getString("tab_bmp_fonts_common_checkbox_fwt")); // NOI18N

        javax.swing.GroupLayout alphabetSettingsPanelLayout = new javax.swing.GroupLayout(alphabetSettingsPanel);
        alphabetSettingsPanel.setLayout(alphabetSettingsPanelLayout);
        alphabetSettingsPanelLayout.setHorizontalGroup(
            alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, alphabetSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bitmapFWTCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, Short.MAX_VALUE)
                    .addGroup(alphabetSettingsPanelLayout.createSequentialGroup()
                        .addGroup(alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alphabetSizeLabel)
                            .addComponent(sideLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sideCharSpinner)
                            .addComponent(alphabetSizeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))))
                .addContainerGap())
        );
        alphabetSettingsPanelLayout.setVerticalGroup(
            alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alphabetSettingsPanelLayout.createSequentialGroup()
                .addGroup(alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alphabetSizeLabel)
                    .addComponent(alphabetSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(alphabetSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sideCharSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sideLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bitmapFWTCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("tab_bmp_fonts_additional_settings"))); // NOI18N

        strikethroughCheckBox.setText(bundle.getString("tab_bmp_fonts_additional_settings_strikethrough")); // NOI18N
        strikethroughCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                strikethroughCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(strikethroughCheckBox)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(strikethroughCheckBox)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout fontsSettingsPanelLayout = new javax.swing.GroupLayout(fontsSettingsPanel);
        fontsSettingsPanel.setLayout(fontsSettingsPanelLayout);
        fontsSettingsPanelLayout.setHorizontalGroup(
            fontsSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fontsSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alphabetSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        fontsSettingsPanelLayout.setVerticalGroup(
            fontsSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fontsSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fontsSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(fontsSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(alphabetSettingsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        operationsTabbedPane.addTab(bundle.getString("tab_bmp_fonts_caption"), fontsSettingsPanel); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("tab_effects_outline_caption"))); // NOI18N

        outlineCheckBox.setText(bundle.getString("tab_effects_outline_checkbox")); // NOI18N
        outlineCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outlineCheckBoxItemStateChanged(evt);
            }
        });

        outlineColorLabel.setBackground(new java.awt.Color(0, 0, 0));
        outlineColorLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        outlineColorLabel.setEnabled(false);
        outlineColorLabel.setOpaque(true);
        outlineColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                outlineColorLabelMousePressed(evt);
            }
        });

        cOutlineColorLabel.setText(bundle.getString("tab_effects_outline_color_border")); // NOI18N

        jLabel1.setText(bundle.getString("tab_effects_outline_line_width")); // NOI18N

        lineWidthSpinner.setModel(outlineWidthSpinnerModel);
        lineWidthSpinner.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cOutlineColorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(outlineColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lineWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(outlineCheckBox))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(outlineCheckBox)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outlineColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lineWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cOutlineColorLabel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout effectsPanelLayout = new javax.swing.GroupLayout(effectsPanel);
        effectsPanel.setLayout(effectsPanelLayout);
        effectsPanelLayout.setHorizontalGroup(
            effectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(effectsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(518, Short.MAX_VALUE))
        );
        effectsPanelLayout.setVerticalGroup(
            effectsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(effectsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        operationsTabbedPane.addTab(bundle.getString("tab_effects_caption"), effectsPanel); // NOI18N

        applyButton.setText(bundle.getString("common_settings_button_apply")); // NOI18N
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        customFontLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        customFontLabel.setAlignmentX(0.5F);
        customFontLabel.setAlignmentY(0.3F);
        customFontLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        savePictureButton.setText(bundle.getString("save_picture_button")); // NOI18N
        savePictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePictureButtonActionPerformed(evt);
            }
        });

        textFont.setEditable(false);
        textFont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFontMouseClicked(evt);
            }
        });

        copyButton.setText("Copy font as text");

        fileMenu.setText("File");

        openMenuItem.setText("Open project");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save project");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);
        fileMenu.add(jSeparator1);

        exitMenuItem.setText("Exit");
        exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMenuItemMousePressed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(operationsTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(mainSplitPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(customFontLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFont, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(copyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savePictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(operationsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(savePictureButton)
                        .addComponent(applyButton)
                        .addComponent(textFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(copyButton))
                    .addComponent(customFontLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadFontButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFontButtonActionPerformed
        if( isNewFont ) {
            if( customFontCheckBox.isSelected() ) { //при избиране на лока
                this.dialogOperations(evt);
                if(choosedFont != null) {
                    if( !choosedFont.equals(currentFont) ) {
                        showFontPanelTable.setCustomFont(choosedFont);
                        showFontPanelTable.setData();
                        showFontPanelTable.setPhysicalFont("");
                        showImageFont.setCustomFont(choosedFont);
                        showImageFont.setPhysicalFont("");
                        pt.setFontType( Constants.FONT_TYPE_CUSTOM );
                        currentFont = choosedFont;
                        isNewFont = true;
                    }
                }
            } else {
                currentFont = fonts.getModel().getSelectedItem().toString();
                customFontLabel.setText(currentFont);
                showFontPanelTable.setPhysicalFont(currentFont);
                showFontPanelTable.setData();
                showFontPanelTable.setCustomFont("");
                showImageFont.setPhysicalFont(currentFont);
                showImageFont.setCustomFont("");
                pt.setFontType( Constants.FONT_TYPE_PHYSICAL );
                isNewFont = false;
            }
            showImageFont.repaint();
        }
    }//GEN-LAST:event_loadFontButtonActionPerformed

    private void customFontCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_customFontCheckBoxItemStateChanged
        if(customFontCheckBox.isSelected()) {
            fonts.setEnabled(false);
        } else {
            showFontPanelTable.setCustomFont("");
            choosedFont = "";
            fonts.setEnabled(true);
        }
        isNewFont = true;
    }//GEN-LAST:event_customFontCheckBoxItemStateChanged

    private void clearAllButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllButtonMouseClicked
        showCustomFont.clearAllButton();
        textFont.setText("");
        showImageFont.repaint();
    }//GEN-LAST:event_clearAllButtonMouseClicked

    private void clearSelRowsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearSelRowsButtonMouseClicked
        showCustomFont.clearSelectedRows();
        ProjectTools.statuses.remove(ProjectTools.projectStatus[0]);
        ProjectTools.statuses.remove(ProjectTools.projectStatus[3]);
        //статус незаписан
        ProjectTools.statuses.put(ProjectTools.projectStatus[2],ProjectTools.projectStatus[2]);
    }//GEN-LAST:event_clearSelRowsButtonMouseClicked

    private void exitMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMousePressed
        this.exitFromProgram();
    }//GEN-LAST:event_exitMenuItemMousePressed

    private void clearCellButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearCellButtonMouseClicked
        showCustomFont.clearSelectedCell();
        showImageFont.setData(showCustomFont.getData());
        ProjectTools.statuses.remove(ProjectTools.projectStatus[0]);
        ProjectTools.statuses.remove(ProjectTools.projectStatus[3]);
        //статус незаписан
        ProjectTools.statuses.put(ProjectTools.projectStatus[2],ProjectTools.projectStatus[2]);
    }//GEN-LAST:event_clearCellButtonMouseClicked

    private void boldCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boldCheckBoxItemStateChanged
        FontEffects.clearEffects();
        if(boldCheckBox.isSelected()) {
            FontEffects.addEffect(FontEffects.EFFECTS.get("BOLD"));
        } else {
            FontEffects.removeEffect(FontEffects.EFFECTS.get("BOLD"));
        }
    }//GEN-LAST:event_boldCheckBoxItemStateChanged

    private void italicCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_italicCheckBoxItemStateChanged
        FontEffects.clearEffects();
        if(italicCheckBox.isSelected()) {
            FontEffects.addEffect(FontEffects.EFFECTS.get("ITALIC"));
        } else {
            FontEffects.removeEffect(FontEffects.EFFECTS.get("ITALIC"));
        }
    }//GEN-LAST:event_italicCheckBoxItemStateChanged

    private void colorLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorLabelMousePressed
        newColorFont = JColorChooser.showDialog(this, choosedFont, Color.gray);
        if ( newColorFont != null ) {
            colorLabel.setForeground(newColorFont);
            colorLabel.setBackground(newColorFont);
            colorLabel.setOpaque(true);
            colorLabel.repaint();
            showImageFont.setFontColor(newColorFont);
        }
    }//GEN-LAST:event_colorLabelMousePressed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        AboutDialog ab;
        ab = new AboutDialog();
        ab.ShowAboutDialog();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        this.dialogOperations(evt);
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        this.dialogOperations(evt);
        this.initShowImageFont();
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void fontsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fontsItemStateChanged
        isNewFont = true;
    }//GEN-LAST:event_fontsItemStateChanged

    private void savePictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePictureButtonActionPerformed
        this.dialogOperations(evt);
    }//GEN-LAST:event_savePictureButtonActionPerformed

    private void outlineCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outlineCheckBoxItemStateChanged
        FontEffects.clearEffects();
        if(outlineCheckBox.isSelected()) {
            FontEffects.isOutlineEffect = true;
            outlineColorLabel.setEnabled(true);
            lineWidthSpinner.setEnabled(true);
        } else {
            FontEffects.isOutlineEffect = false;
            outlineColorLabel.setEnabled(false);
            lineWidthSpinner.setEnabled(false);
        }

    }//GEN-LAST:event_outlineCheckBoxItemStateChanged

    private void outlineColorLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outlineColorLabelMousePressed
        if ( outlineCheckBox.isSelected() ) {
            newColorOutline = JColorChooser.showDialog(this, null, Color.gray);
            if ( newColorOutline != null) {
                outlineColorLabel.setForeground(newColorOutline);
                outlineColorLabel.setBackground(newColorOutline);
                outlineColorLabel.setOpaque(true);
                outlineColorLabel.repaint();
                showImageFont.repaint();
            }
        }
    }//GEN-LAST:event_outlineColorLabelMousePressed

    private void strikethroughCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_strikethroughCheckBoxItemStateChanged
        FontEffects.clearEffects();
        if(strikethroughCheckBox.isSelected()) {
            FontEffects.addEffect(FontEffects.EFFECTS.get("STRIKETHROUGH"));
        } else {
            FontEffects.removeEffect(FontEffects.EFFECTS.get("STRIKETHROUGH"));
        }
    }//GEN-LAST:event_strikethroughCheckBoxItemStateChanged

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        this.initShowImageFont();
        //запис
        if ( ProjectTools.statuses.containsKey(ProjectTools.projectStatus[1]) ) { //ако е вече отворен
            ProjectTools.statuses.remove(ProjectTools.projectStatus[0]);
            ProjectTools.statuses.remove(ProjectTools.projectStatus[2]);
            //статус записан
            ProjectTools.statuses.put(ProjectTools.projectStatus[3],ProjectTools.projectStatus[3]);
            this.setForSave();
        } else { // ако е нов и трябва да се записва
            dialogOperations(evt);
        }
    }//GEN-LAST:event_applyButtonActionPerformed

    private void textFontMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFontMouseClicked
        clipboardOp.setComponentAction(textFont, Constants.CLIPBOARD_ACTIONS_COPY);
        textFont.addActionListener(clipboardOp);

    }//GEN-LAST:event_textFontMouseClicked

    /**
     * Позволява многократно използване на dialogChooser
     * @param evt
     */
    private void dialogOperations(java.awt.event.ActionEvent evt) {
        //това се прави за да се изчисти File name от диалога
        File f = new File("");
        dialogChooser.setSelectedFile(f);
        
        if (evt.getSource() == loadFontButton) { //зареждане на шрифт
            dialogChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            dialogChooser.setDialogTitle( langResources.getString("dialog_caption_load_font"));
            dialogChooser.setFileFilter(ttff);

            int returnVal = dialogChooser.showOpenDialog(MainFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {//open
                File file = dialogChooser.getSelectedFile();
                pt.setCustomFontPath(file.getAbsolutePath());
                choosedFont = file.getName();
                if (choosedFont.endsWith(".ttf")){
                    customFontLabel.setText(choosedFont);
                } else {
                    choosedFont = "";
                }
            } else { // cancel
                choosedFont = "";
            }
        } else if (evt.getSource() == saveMenuItem || evt.getSource() == applyButton) { //запис на проект
            dialogChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            dialogChooser.setDialogTitle( langResources.getString("dialog_caption_save_project"));
            dialogChooser.setFileFilter(projectFilter);

            int returnVal = dialogChooser.showSaveDialog(MainFrame.this);
            String projectName = "";
            if (returnVal == JFileChooser.APPROVE_OPTION) {//save
                projectName = dialogChooser.getSelectedFile().getName();
                pt.setAbsolutePath(dialogChooser.getSelectedFile().getAbsolutePath());
                pt.setProjectName(projectName);
                ProjectTools.statuses.remove(ProjectTools.projectStatus[0]);
                ProjectTools.statuses.remove(ProjectTools.projectStatus[2]);
                //статус отворен
                ProjectTools.statuses.put(ProjectTools.projectStatus[1], ProjectTools.projectStatus[1]);
                //статус записан
                ProjectTools.statuses.put(ProjectTools.projectStatus[3],ProjectTools.projectStatus[3]);
                this.setForSave();
                this.setTitle( MainFrame.ps.getField("Name") + " - " + dialogChooser.getSelectedFile().getName());
            }
        } else if(evt.getSource() == openMenuItem) { //отваряне на проект
            textFont.setText("");
            dialogChooser.setDialogType( JFileChooser.OPEN_DIALOG );
            dialogChooser.setDialogTitle( langResources.getString("dialog_caption_open_project"));
            dialogChooser.setFileFilter(projectFilter);
            int returnVal = dialogChooser.showOpenDialog(MainFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {//open
                this.setTitle( MainFrame.ps.getField("Name") + " - " + dialogChooser.getSelectedFile().getName());
                if (dialogChooser.getSelectedFile().getName().endsWith(".mfp")) {
                    pt.setAbsolutePath(dialogChooser.getSelectedFile().getAbsolutePath());
                    //зареждаме данните от проекта
                    pt.loadProject();

                    String fontType = pt.getFontType();
                    String fontName = pt.getFontName();
                    String customFontPath = pt.getCustomFontPath();

                    if( fontType.equals(Constants.FONT_TYPE_PHYSICAL) ) {// Os fonts
                        showFontPanelTable.setPhysicalFont(fontName);
                        showFontPanelTable.setData();
                        showFontPanelTable.setCustomFont("");
                        showImageFont.setPhysicalFont(fontName);
                        showImageFont.setCustomFont("");
                        fonts.setSelectedItem(fontName);
                        pt.setFontType( Constants.FONT_TYPE_PHYSICAL );
                        customFontLabel.setText(fontName);
                    } else if( fontType.equals(Constants.FONT_TYPE_LOGICAL) ) { // Java fonts
                        showFontPanelTable.setLogicalFont();
                        showFontPanelTable.setCustomFont("");
                        showFontPanelTable.setPhysicalFont("");
                        showFontPanelTable.setData();
                        showImageFont.setPhysicalFont("");
                        showImageFont.setCustomFont("");
                        pt.setFontType( Constants.FONT_TYPE_LOGICAL );
                        customFontLabel.setText("");
                    } else if( fontType.equals(Constants.FONT_TYPE_CUSTOM) ) { // loaded fonts
                        showFontPanelTable.setCustomFont(customFontPath);
                        showFontPanelTable.setData();
                        showFontPanelTable.setPhysicalFont("");
                        showImageFont.setCustomFont(customFontPath);
                        showImageFont.setPhysicalFont("");
                        pt.setFontType( Constants.FONT_TYPE_CUSTOM );
                        fonts.setEnabled(false);
                        customFontCheckBox.setSelected(true);
                        customFontLabel.setText(fontName);
                    }

                    this.fontSizeSpinner.setValue( Integer.parseInt(pt.getFontSize()) );
                    this.italicCheckBox.setSelected( pt.isItalic() );
                    this.boldCheckBox.setSelected( pt.isBold() );

                    this.outlineCheckBox.setSelected( pt.isOutline() );
                    newColorOutline = pt.getColorOutline();
                    //зареждане на цвета за бордера на буквите
                    outlineColorLabel.setForeground( newColorOutline );
                    outlineColorLabel.setBackground( newColorOutline );
                    outlineColorLabel.setOpaque(true);
                    outlineColorLabel.repaint();
                    //размер на бордера
                    lineWidthSpinner.setValue( pt.getOutlineLineWidth() );

                    //зареждане на цвета за шрифта
                    newColorFont = pt.getColor();
                    colorLabel.setForeground( newColorFont );
                    colorLabel.setBackground( newColorFont );
                    colorLabel.setOpaque(true);
                    colorLabel.repaint();
                    showImageFont.setFontColor( newColorFont );

                    this.alphabetSizeTextField.setText( pt.getAlphabetSize() );
                    this.sideCharSpinner.setValue( Integer.parseInt(pt.getSideChar()) );
                    bitmapFWTCheckBox.setSelected( pt.isBitmapFWT() );
                    showCustomFont.setStringData(pt.getNumberData());
                    //не е нов
                    ProjectTools.statuses.remove(ProjectTools.projectStatus[0]);
                    ProjectTools.statuses.remove(ProjectTools.projectStatus[2]);
                    //статус отворен
                    ProjectTools.statuses.put(ProjectTools.projectStatus[1],ProjectTools.projectStatus[1]);
                    //статус записан
                    ProjectTools.statuses.put(ProjectTools.projectStatus[3],ProjectTools.projectStatus[3]);
                }
          }
        } else if(evt.getSource().equals(savePictureButton)) { //запис на картинка
            dialogChooser.setDialogType( JFileChooser.SAVE_DIALOG );
            dialogChooser.setDialogTitle( langResources.getString("dialog_caption_save_picture"));
            dialogChooser.setFileFilter(picf);
            
            int returnVal = dialogChooser.showSaveDialog(MainFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {//open
                File t1 = new File(Constants.TMP_PICTURE);
                File t2 = new File(dialogChooser.getSelectedFile().getAbsolutePath()+".png");
                try {
                    ProjectTools.copyFile(t1, t2);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void setForSave() {

        pt.setFontName(MainFrame.showCustomFont.getFont().getFontName());
        pt.SetFontSize(this.fontSizeSpinner.getValue().toString());
        pt.setItalic(this.italicCheckBox.isSelected());
        pt.setBold(this.boldCheckBox.isSelected());
        pt.setColor(this.colorLabel.getBackground());

        pt.setOutline( this.outlineCheckBox.isSelected() );
        pt.setColorOutline(this.outlineColorLabel.getBackground());
        pt.setOutlineLineWidth((Float)lineWidthSpinner.getValue());

        pt.setAlphabetSize(this.alphabetSizeTextField.getText());

        pt.setSideChar(this.sideCharSpinner.getValue().toString());
        pt.setBitmapFWT(MainFrame.bitmapFWTCheckBox.isSelected());
        pt.setNumberData(MainFrame.showCustomFont.getNumberData().toString());
        pt.saveProject();

    }
    private void initShowImageFont() {
        showImageFont.setFontSize( Integer.decode(fontSizeSpinner.getModel().getValue().toString()) );
        showImageFont.setAlphabetSize(Integer.decode(alphabetSizeTextField.getText()));
        showImageFont.setSide( Integer.decode(sideCharSpinner.getModel().getValue().toString()) );
        showImageFont.setData(showCustomFont.getData());
        //заради resize при зареждане на проект
        showImageFont.paintComponent(showImageFont.getGraphics());
        jPanel4.setPreferredSize(new Dimension(showImageFont.getImageWidth(), 547));
        jPanel4.setSize(showImageFont.getImageWidth(), 547);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    class AlphabetSizeVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            javax.swing.JTextField tf = (javax.swing.JTextField) input;
            String testField = tf.getText();
            try {
                if( Integer.decode(testField) <= 0 ) {
                    tf.setText("22");
                }
            } catch(java.lang.NumberFormatException ex) {
                tf.setText("22");
            }
            return true;
        }
    }

    /**
     * Филтър на диалога за проектните файлове
     */
    class ProjectFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".mfp");
        }
        public String getDescription() {
            return "MagicFont project files (*.mfp)";
        }
    }

    /**
     * Филтър на диалога за шрифтове
     */
    class TTFFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".ttf");
        }
        public String getDescription() {
            return "True type fonts (*.ttf)";
        }
    }

    /**
     * Филтър на диалога за png
     */
    class PictureFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".png");
        }
        public String getDescription() {
            return "*.png";
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel alphabetSettingsPanel;
    private javax.swing.JLabel alphabetSizeLabel;
    private javax.swing.JTextField alphabetSizeTextField;
    private javax.swing.JButton applyButton;
    public static javax.swing.JCheckBox bitmapFWTCheckBox;
    private javax.swing.JCheckBox boldCheckBox;
    private javax.swing.JLabel cColorLabel;
    private javax.swing.JLabel cOutlineColorLabel;
    private javax.swing.JLabel choiceFontLabel;
    private javax.swing.JButton clearAllButton;
    private javax.swing.JButton clearCellButton;
    private javax.swing.JPanel clearPanel;
    private javax.swing.JButton clearSelRowsButton;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JButton copyButton;
    private javax.swing.JCheckBox customFontCheckBox;
    private javax.swing.JLabel customFontLabel;
    private javax.swing.JScrollPane customFontScrollPane;
    private javax.swing.JPanel effectsPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel fontSizeLabel;
    private javax.swing.JSpinner fontSizeSpinner;
    private javax.swing.JComboBox fonts;
    private javax.swing.JPanel fontsSettingsPanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBox italicCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    protected static javax.swing.JSpinner lineWidthSpinner;
    private javax.swing.JButton loadFontButton;
    private javax.swing.JPanel loadFontPanel;
    private javax.swing.JPanel loadFontsPanel;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JTabbedPane operationsTabbedPane;
    private javax.swing.JCheckBox outlineCheckBox;
    private javax.swing.JLabel outlineColorLabel;
    private javax.swing.JSplitPane rightSplitPane;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton savePictureButton;
    private javax.swing.JScrollPane showFontScrollPane;
    private javax.swing.JSpinner sideCharSpinner;
    private javax.swing.JLabel sideLabel;
    private javax.swing.JCheckBox strikethroughCheckBox;
    protected static javax.swing.JTextField textFont;
    private javax.swing.JScrollPane toImageScrollPane;
    // End of variables declaration//GEN-END:variables

}
