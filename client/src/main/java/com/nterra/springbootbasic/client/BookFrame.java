package com.nterra.springbootbasic.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookFrame extends JFrame {

  private JTextField titleField, authorField, idField;
  private JButton addButton, updateButton, deleteButton, loadButton;
  private JTable table;
  private DefaultTableModel model;
  private BookService selecedService;

  public BookFrame(BookService plainJavaService, BookService springService,
      BookService springBootService) {
    setTitle("Book Management");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(5, 1));


    // Backend panel
    JPanel backendPanel = new JPanel();
    ButtonGroup backendButtonGroup = new ButtonGroup();
    JRadioButton plainJavaBackend = new JRadioButton("PlainJava");
    plainJavaBackend.setSelected(true);
    selecedService = plainJavaService;
    backendButtonGroup.add(plainJavaBackend);
    plainJavaBackend.addActionListener(
        event -> selecedService = plainJavaService
    );
    backendPanel.add(plainJavaBackend);
    JRadioButton springBackend = new JRadioButton("Spring");
    springBackend.setSelected(false);
    backendButtonGroup.add(springBackend);
    springBackend.addActionListener(
        event -> selecedService = springService
    );
    backendPanel.add(springBackend);
    JRadioButton springBootBackend = new JRadioButton("Spring Booot");
    springBootBackend.setSelected(false);
    backendButtonGroup.add(springBootBackend);
    springBootBackend.addActionListener(
        event -> selecedService = springBootService
    );
    backendPanel.add(springBootBackend);
    add(backendPanel);

    // Form panel
    JPanel formPanel = new JPanel(new GridLayout(3, 2));
    formPanel.add(new JLabel("ID:"));
    idField = new JTextField();
    idField.setEditable(false);
    formPanel.add(idField);

    formPanel.add(new JLabel("Title:"));
    titleField = new JTextField();
    formPanel.add(titleField);

    formPanel.add(new JLabel("Author:"));
    authorField = new JTextField();
    formPanel.add(authorField);

    add(formPanel, BorderLayout.NORTH);

    // Table setup
    model = new DefaultTableModel(new String[]{"ID", "Title", "Author"}, 0);
    table = new JTable(model);
    add(new JScrollPane(table));

    // Button panel
    JPanel buttonPanel = new JPanel();
    addButton = new JButton("Add");
    addButton.addActionListener(e -> addBook());
    updateButton = new JButton("Update");
    updateButton.addActionListener(e -> updateBook());
    deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> deleteBook());
    loadButton = new JButton("(Re)load");
    loadButton.addActionListener(e -> refreshTable());

    buttonPanel.add(addButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(loadButton);
    add(buttonPanel, BorderLayout.SOUTH);

    table.getSelectionModel().addListSelectionListener(event -> {
      if (table.getSelectedRow() != -1) {
        int selectedRow = table.getSelectedRow();
        idField.setText(table.getValueAt(selectedRow, 0).toString());
        titleField.setText(table.getValueAt(selectedRow, 1).toString());
        authorField.setText(table.getValueAt(selectedRow, 2).toString());
      }
    });
  }

  private void addBook() {
    String title = titleField.getText();
    String author = authorField.getText();

    Book book = new Book(0, title, author);
    selecedService.addBook(book);
    refreshTable();
    clearFields();
  }

  private void updateBook() {
    int id = Integer.parseInt(idField.getText());
    String title = titleField.getText();
    String author = authorField.getText();

    Book book = new Book(id, title, author);
    selecedService.updateBook(book);
    refreshTable();
    clearFields();
  }

  private void deleteBook() {
    int id = Integer.parseInt(idField.getText());
    selecedService.deleteBook(id);
    refreshTable();
    clearFields();
  }

  private void refreshTable() {
    model.setRowCount(0);
    for (Book book : selecedService.getAllBooks()) {
      model.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor()});
    }
  }

  private void clearFields() {
    idField.setText("");
    titleField.setText("");
    authorField.setText("");
  }

}
