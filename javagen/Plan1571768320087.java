public class Plan1571768320087 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

DecreaseTraffic("A");

}

}
}
