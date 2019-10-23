public class Plan1571768138743 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
StartServer("B");
StartServer("C");

}

if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

StartServer("A");

}

StartServer("C");

} else {
StartServer("B");
}


}
}
