public class Plan1571771716817 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}


}

StartServer("C");
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}



}
}
