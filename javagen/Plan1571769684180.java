public class Plan1571769684180 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 5 ; i++) {
StartServer("A");
}


for (int i = 0; i < 2 ; i++) {
StartServer("C");
}


} else {
StartServer("B");
}

}

}
}
