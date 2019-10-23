public class Plan1571767797819 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}


}
}
