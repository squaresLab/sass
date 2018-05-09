public class Plan1525888975830 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("C");
}

}




}

}
}
