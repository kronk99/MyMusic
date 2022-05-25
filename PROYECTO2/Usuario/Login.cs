using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms; 

namespace Usuario
{
    public partial class Login : Form
    {
        private Server server;
        public Login()
        {
            server= new Server();
            InitializeComponent();
        }

        private void btnRegistrarse_Click(object sender, EventArgs e)
        {
            registrarse1.Visible = true;
        }

        private void btnIngresar_Click(object sender, EventArgs e)
        {
        
        }
        private void controlboton1()
        {
            if (txtUsuario.Text.Trim() != String.Empty )
            {
                btnIngresar.Enabled = true;
                errorProvider1.SetError(txtUsuario, "");
            }
            else
            {
                errorProvider1.SetError(txtUsuario, "debe introducir su nombre");
                btnIngresar.Enabled = false;
            }
        }


        private void Login_Load(object sender, EventArgs e)
        {
            btnIngresar.Enabled = false;
            registrarse1.Visible = false;
        }

        private void txtUsuario_TextChanged(object sender, EventArgs e)
        {
            controlboton1();
        }

        private void txtContraseña_TextChanged(object sender, EventArgs e)
        {
            // controlboton1(); modificar despues, pereza hacer solo gui y nada de logica de codigo
        }

        private void registrarse1_Load(object sender, EventArgs e)
        {

        }
    }
}
