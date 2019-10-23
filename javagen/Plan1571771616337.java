public class Plan1571771616337 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("C");
}


StartServer("C");
StartServer("B");


}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");

}


}
}
