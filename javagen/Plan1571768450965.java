public class Plan1571768450965 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("A");
for (int i = 0; i < 5 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}




StartServer("A");

}
}
