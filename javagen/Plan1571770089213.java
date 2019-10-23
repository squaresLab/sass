public class Plan1571770089213 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("A");

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("B");
StartServer("A");


} else {
StartServer("C");
}

}


}
}
