package br.com.cliente.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import br.com.cliente.bean.Cliente;
import br.com.cliente.controle.ClienteCT;
import br.com.cliente.util.MaskCampos;

public class FormCliente extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JFormattedTextField txtCep;
	private JFormattedTextField txtCel;
	private JFormattedTextField txtCpf;
	private JTextField txtID;
	private JComboBox cbPesquisar;
	private ButtonGroup bt = new ButtonGroup();
	
	private String genero;

	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnPesquisar;
	private JButton btnLimpar;
	
	public Cliente montaCliente() { 
	//Pega dos dados digitados nos campos do formulário e atribui ao objeto da classe Cliente;
		Cliente c = new Cliente();
		c.setNome(this.txtNome.getText());
		c.setEndereco(this.txtEndereco.getText());
		c.setMunicipio(this.txtMunicipio.getText());
		c.setCep(this.txtCep.getText());
		c.setCel(this.txtCel.getText());
		c.setCpf(this.txtCpf.getText());
		c.setGenero(genero);
		
		if(this.txtID.getText() != null && !this.txtID.getText().equals("")) {
			c.setId(Integer.parseInt(this.txtID.getText()));
			//Condicional para garantir que o campo ID não seja nulo;
		}
		return c;
	} 
	
	public void limpaTela() {
		//Limpa os campos do formulário;
		this.txtNome.setText("");
		this.txtEndereco.setText("");
		this.txtMunicipio.setText("");
		this.txtCep.setText("");
		this.txtCel.setText("");
		this.txtCpf.setText("");
		this.txtID.setText(null);
		this.rdbtnF.setSelected(false);
		this.rdbtnM.setSelected(false);
	}
	
	public void setCliente(Cliente c){ 
		//Preenche formulário de Cliente selecionado no combobox, ao clicar no botão pesquisar;
		this.txtNome.setText(c.getNome());
		this.txtEndereco.setText(c.getEndereco());
		this.txtMunicipio.setText(c.getMunicipio());
		this.txtCep.setText(c.getCep());
		this.txtCel.setText(c.getCel());
		this.txtCpf.setText(c.getCpf());
		if(c.getGenero().equals("F")) {
			this.rdbtnF.setSelected(true);
			genero = "F";
		} else if(c.getGenero().equals("M")) {
			this.rdbtnM.setSelected(true);
			genero = "M";
		}
		//Condicional para seleção do radiobutton do registro selecionado;
		if(c.getId() != null && c.getId() > 0) {
			this.txtID.setText(c.getId().toString());
		} //Condicional para garantir que o campo ID não seja nulo;
	}
	
	public void carregaLista(){ 
		//Preenche Combobox com registros do banco de dados
		ClienteCT mbc = new ClienteCT();
		
		List ClienteBd = mbc.select();
		cbPesquisar.removeAllItems();
		for (Cliente Medico : ClienteBd) {
			cbPesquisar.addItem(Medico);
		}
	}

	public FormCliente() {
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblPesquisar.setBounds(10, 31, 109, 14);
		contentPane.add(lblPesquisar);

		JLabel lblNome = new JLabel("Nome/Empresa:");
		lblNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblNome.setBounds(10, 79, 109, 14);
		contentPane.add(lblNome);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblEndereco.setBounds(10, 104, 109, 14);
		contentPane.add(lblEndereco);

		JLabel lblNewLabel = new JLabel("Munic\u00EDpio:");
		lblNewLabel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 129, 109, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCep = new JLabel("Cep:");
		lblCep.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblCep.setBounds(10, 154, 109, 14);
		contentPane.add(lblCep);
		
		JLabel lblCel = new JLabel("Cel:");
		lblCel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblCel.setBounds(10, 204, 109, 14);
		contentPane.add(lblCel);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblCpf.setBounds(10, 229, 109, 14);
		contentPane.add(lblCpf);
		
		JLabel lblGenero = new JLabel("G\u00EAnero:");
		lblGenero.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblGenero.setBounds(10, 279, 109, 14);
		contentPane.add(lblGenero);
		
		cbPesquisar = new JComboBox();
		cbPesquisar.setEditable(true);
		cbPesquisar.setBounds(129, 28, 283, 20);
		AutoCompleteDecorator.decorate(cbPesquisar);
		contentPane.add(cbPesquisar);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		txtNome.setBounds(129, 76, 283, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		txtEndereco.setBounds(129, 101, 365, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		txtMunicipio.setBounds(129, 126, 143, 20);
		contentPane.add(txtMunicipio);
		txtMunicipio.setColumns(10);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				genero = "M";
			}
		});
		rdbtnM.setBounds(125, 275, 45, 23);
		contentPane.add(rdbtnM);

		rdbtnF = new JRadioButton("F");
		rdbtnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genero = "F";
			}
		});
		rdbtnF.setBounds(172, 275, 109, 23);
		contentPane.add(rdbtnF);
		
		bt.add(rdbtnF);
		bt.add(rdbtnM);
		
		MaskCampos mc = new MaskCampos();
		
		try {
			txtCep = new JFormattedTextField(mc.maskCep(txtCep));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCep.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		txtCep.setBounds(129, 151, 143, 20);
		contentPane.add(txtCep);

		try {
			txtCel = new JFormattedTextField(mc.maskCel(txtCel));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		txtCel.setBounds(129, 201, 143, 20);
		contentPane.add(txtCel);

		try {
			txtCpf = new JFormattedTextField(mc.maskCpf(txtCpf));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCpf.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		txtCpf.setBounds(129, 226, 143, 20);
		contentPane.add(txtCpf);
		
		btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/save.png")));
		btnSalvar.setBounds(193, 327, 28, 23);
		btnSalvar.addActionListener(this);
		btnSalvar.setActionCommand("salvar");
		contentPane.add(btnSalvar);

		btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/edit.png")));
		btnEditar.setBounds(231, 327, 28, 23);
		btnEditar.addActionListener(this);
		btnEditar.setActionCommand("editar");
		contentPane.add(btnEditar);

		btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/clean.png")));
		btnLimpar.setBounds(269, 327, 28, 23);
		btnLimpar.addActionListener(this);
		btnLimpar.setActionCommand("limpar");
		contentPane.add(btnLimpar);

		btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(FormCliente.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(308, 327, 28, 23);
		btnExcluir.addActionListener(this);
		btnExcluir.setActionCommand("excluir");
		contentPane.add(btnExcluir);

		btnPesquisar = new JButton("");
		btnPesquisar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(422, 22, 28, 23);
		btnPesquisar.addActionListener(this);
		btnPesquisar.setActionCommand("pesquisar");
		contentPane.add(btnPesquisar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
		lblId.setBounds(10, 54, 46, 14);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(129, 51, 45, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		btnSalvar = new JButton("");  
			btnSalvar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/save.png")));  
			btnSalvar.setBounds(193, 327, 28, 23);  
			btnSalvar.addActionListener(this);  
			btnSalvar.setActionCommand("salvar");  
			contentPane.add(btnSalvar);  
		
			btnEditar = new JButton("");  
			btnEditar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/edit.png")));  
			btnEditar.setBounds(231, 327, 28, 23);  
			btnEditar.addActionListener(this);  
			btnEditar.setActionCommand("editar");  
			contentPane.add(btnEditar);  
		
			btnLimpar = new JButton("");  
			btnLimpar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/clean.png")));  
			btnLimpar.setBounds(269, 327, 28, 23);  
			btnLimpar.addActionListener(this);  
			btnLimpar.setActionCommand("limpar");  
			contentPane.add(btnLimpar);  
		
			btnExcluir = new JButton("");  
			btnExcluir.setIcon(new ImageIcon(FormCliente.class.getResource("/img/delete.png")));  
			btnExcluir.setBounds(308, 327, 28, 23);  
			btnExcluir.addActionListener(this);  
			btnExcluir.setActionCommand("excluir");  
			contentPane.add(btnExcluir);  
		
			btnPesquisar = new JButton("");  
			btnPesquisar.setIcon(new ImageIcon(FormCliente.class.getResource("/img/search.png")));  
			btnPesquisar.setBounds(422, 22, 28, 23);  
			btnPesquisar.addActionListener(this);  
			btnPesquisar.setActionCommand("pesquisar");  
			contentPane.add(btnPesquisar);  
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
			//Condicional - se clicar no botão Salvar (disquete)...
			Cliente c = this.montaCliente();
			//Chama o método montaCliente para pegar os dados e gravar no objeto Cliente c;
			ClienteCT cbc = new ClienteCT();
			//Instancia a classe de controle ClienteCT;
			cbc.insert(c);
			//Chama o método insert da classe ClienteCT para inserir os dados do objeto Cliente (c) de montaCliente no banco;
			this.limpaTela();
			//Limpa os campos após inserir/salvar dados no banco;
			this.carregaLista();
			//Carrega a lista do combobox, atualizando após inserção;
			JOptionPane.showMessageDialog(null, "Cliente "+txtNome.getText()+" cadastrado...");
			//Abre diálogo de mensagem, informando que o cliente foi cadastrado;
		} else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
				//Condicional adicional - se clicar no botão Selecionar (lupa)...
				if(cbPesquisar.getSelectedItem() != null && !cbPesquisar.getSelectedItem().equals("")) {
				//Condicional - se o campo de pesquisa NÃO estiver em branco ao clicar em Selecionar...
				Cliente c = (Cliente) cbPesquisar.getSelectedItem();
				//Cast - pega o item selecionado no combobox e transforma num objeto Cliente;
				this.setCliente(c);
				//Chama o método setCliente para preencher os campos do formulário a partir do objeto Cliente da seleção do combobox;
				} else {
				JOptionPane.showMessageDialog(null, "Escolha um cliente na lista para pesquisar.");
				//Caso o campo combobox esteja em branco ao clicar em Selecionar, abre diálogo orientando escolher primeiro um cliente da lista;
				}
				} else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
				//Ao clicar no botão Limpar (ícone-vassoura);
				this.limpaTela();
				//Aciona o método limpaTela, limpando os campos do formulário;
				} else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {
					//Caso o botão acionado seja Excluir (ícone "X");
					String[] options = {"Sim", "Não"};
					//Cria variável tipo vetor para definir botão de seleção ativado por padrão;
					int conf = JOptionPane.showOptionDialog(null, null, "CONFIRMA EXCLUSÃO?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					//Cria variável tipo int com configuração de janela de diálogo de confirmação de exclusão;
					if(conf == JOptionPane.YES_OPTION){
						//Condicional: se a opção for SIM na confirmação...
						Cliente c = this.montaCliente();
						//Chama o método montaCliente para pegar os dados e gravar no objeto Cliente c;
						ClienteCT cbc = new ClienteCT();
						//Instancia a classe de controle ClienteCT;
						cbc.delete(c);
						//Chama o método delete da classe ClienteCT para excluir dados do objeto Cliente (c) no banco;
						this.limpaTela();
						//Limpa os campos após excluir os dados do banco;
						this.carregaLista();
						//Carrega a lista do combobox, atualizando após exclusão;
					}
				} else if (e.getActionCommand().equals(this.btnEditar.getActionCommand())) {
					//Caso o botão acionado seja Editar (ícone Lápis);
					String[] options = {"Sim", "Não"};
					//Cria variável tipo vetor para definir botão de seleção ativado por padrão;
					int conf = JOptionPane.showOptionDialog(null, null, "CONFIRMA EDIÇÃO?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					//Cria variável tipo int com configuração de janela de diálogo de confirmação de edição;
					if(conf == JOptionPane.YES_OPTION) {
						//Condicional: se a opção for SIM na confirmação...
						Cliente c = this.montaCliente();
						//Chama o método montaCliente para pegar os dados e gravar no objeto Cliente c;
						ClienteCT cbc = new ClienteCT();
						//Instancia a classe de controle ClienteCT;
						cbc.update(c);
						//Chama o método update da classe ClienteCT para atualizar dados do objeto Cliente (c) de montaCliente no banco;
						this.limpaTela();
						//Limpa os campos após atualizar os dados do banco;
						this.carregaLista();
						//Carrega a lista do combobox, atualizando após edição;
					}
				}
			}
		}