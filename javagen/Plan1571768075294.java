public class Plan1571768075294 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


} else {
DecreaseTraffic("A");
}

}

}
}
