public class Plan1571775294778 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("A");

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}

StartServer("A");

}


}
}
