public class Plan1571774667685 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}


}


}
}
